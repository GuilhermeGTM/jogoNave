package jogonavetrabalho;



import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Missel {
        //variavel para pega imagem
	private Image imagem;
        //variavel das cordenadas
	private int x, y;
	private int largura, altura;
        //variavel para definir se é visivel ou não
	private boolean isVisivel;
        //contant define para o missel para não passar da largura da tela
	private static final int LARGURA_TELA = 500;
        //constant define a velocidade do missel
	private static final int VELOCIDADE = 5;

        //construtor  do missel
	public Missel(int x, int y) {
                //pega os parametros
		this.x = x;
		this.y = y;
                //puxa a imagem do missel
		ImageIcon referencia = new ImageIcon("res\\missel.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		// torna variavel visivel(true)
		isVisivel = true;
	}
        
        // metodo para dar movimento
	public void mexer(){
		// mpega variavel x do missel e soma com a velocidade
		this.x += VELOCIDADE;
                //define que o missel suma qnd passar do limite da tela(false)
		if(this.x > LARGURA_TELA){
			isVisivel = false;
		}
		
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

	
}
