package jogonavetrabalho;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

// extends a class JPanel pode ser conhecida como componente e ser adicionada 
//no container ActionListener emplementa o timer
public class Fase extends JPanel implements ActionListener {
        // variavel do fundo
	private Image fundo;
        //variavel da nave
	private Nave nave;
        //vai tratar o ambiente para atualizar a tela para fazer a pintura
	private Timer timer;
        // testa se o jogo termino ou se está jogando
	private boolean emJogo;
        //lista de inimigos
	private List<Inimigo> inimigos;
        
        //matris cordenadas
	private int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 },
			{ 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
			{ 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
			{ 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
			{ 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
			{ 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 },
			{ 920, 300 }, { 856, 328 }, { 456, 320 } };

        //construtor
	public Fase() {
                //metodo q deixa a tela em foco
		setFocusable(true);
                //metodo que não deixa a tela ruim tipo listas
		setDoubleBuffered(true);
                //adiciona um evento
		addKeyListener(new TecladoAdapter());
                //puxa a imagem
		ImageIcon referencia = new ImageIcon("res\\fundo.png");
		fundo = referencia.getImage();
                //estanciar o objeto tipo nave
		nave = new Nave();
                // se tive jogando 
		emJogo = true;
                //metodo para inicializar
		inicializaInimigos();
                // delay de 5ms para dar movimento, this define q puxa esa class
		timer = new Timer(5, this);
                //metodo q incia o timer
		timer.start();

	}
        //metodo para iniciar os inimigos
	public void inicializaInimigos() {

		inimigos = new ArrayList<Inimigo>();

		for (int i = 0; i < coordenadas.length; i++) {
			inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));

		}

	}
        // metodo que pinta a tela que recebe como parametro objeto tipo Graphics
	public void paint(Graphics g) {
                
		Graphics2D graficos = (Graphics2D) g;
                //repinta o fundo como ele n tem movimento ta estatico ele fica null
		graficos.drawImage(fundo, 0, 0, null);

		if (emJogo) {
                        //repinta a nave
			graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
                        //cria uma lista e pega o objeto nave
			List<Missel> misseis = nave.getMisseis();
                        
                        //intera com os elementos da lista 
			for (int i = 0; i < misseis.size(); i++) {
                                
                                
				Missel m = (Missel) misseis.get(i);
                                //pega 1 missel da lista
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

			}
                        // pinta os inimigos
			for (int i = 0; i < inimigos.size(); i++) {

				Inimigo in = inimigos.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

			}
                        //coloca o numero  de inimigos na tela
			graficos.setColor(Color.WHITE);
			graficos.drawString("INIMIGOS: " + inimigos.size(), 5, 15);
			
		} else {
			//puxa o game over
			ImageIcon fimJogo = new ImageIcon("res\\game_over.jpg");
			
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
			
			
		}
		//limpa para proxima pintura
		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
                //testase os inimigos morre então vc venceu
		if (inimigos.size() == 0) {
			emJogo = false;
		}
                //pita e remove os missel da tela
		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {
                    
			Missel m = (Missel) misseis.get(i);
                        //define se ele for visivel ele puxa o mexer
			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseis.remove(i);
			}

		}
                // se tiver visivil ele aparece se n ele remove da lista
		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo in = inimigos.get(i);

			if (in.isVisivel()) {
				in.mexer();
			} else {
				inimigos.remove(i);
			}

		}

		nave.mexer();
		checarColisoes();
                //repintar a tela toda vez q o metodo for chamado a cada 5ms
		repaint();
	}
        //metodo para ver se o missel bateu na nave ou nave bateu no inimigo
	public void checarColisoes() {
                // pega o contorno da nave
		Rectangle formaNave = nave.getBounds();
		Rectangle formaInimigo;
		Rectangle formaMissel;
                //testa colisoes com  a nave com inimigos
		for (int i = 0; i < inimigos.size(); i++) {
                        //tempInimigo objeto temporario
			Inimigo tempInimigo = inimigos.get(i);
			formaInimigo = tempInimigo.getBounds();
                        //intersets é um metodo que testa se o contorno dos objetos toca em outro
			if (formaNave.intersects(formaInimigo)) {

				nave.setVisivel(false);
				tempInimigo.setVisivel(false);

				emJogo = false;

			}

		}
                //reebe o misseis da nave
		List<Missel> misseis = nave.getMisseis();
                // testa 1 missel a cada inimigo
		for (int i = 0; i < misseis.size(); i++) {

			Missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {
                                //temporario
				Inimigo tempInimigo = inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();
                                
                                //se o missel toca no inimigo ele some ambos
				if (formaMissel.intersects(formaInimigo)) {

					tempInimigo.setVisivel(false);
					tempMissel.setVisivel(false);

				}

			}

		}

	}
        //pega os eventos da tela e extende a class KeyAdapter
	private class TecladoAdapter extends KeyAdapter {
            //emplementa os metodos
		@Override
                //inicializa o jogo dnv
		public void keyPressed(KeyEvent e) {
			//puxa 
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				emJogo = true;
				nave = new Nave();
				inicializaInimigos();
			}
			
			nave.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}

	}

}
