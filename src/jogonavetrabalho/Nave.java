package jogonavetrabalho;



import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave {
        //cordenadas da nave
	private int x, y;
        //variavel para aumentar o espaçamento das cordenadas
	private int dx, dy;
        //define a posição dos missel
	private int altura, largura;
        //define se aparece ou não , no caso se ta morto é false dai some
	private boolean isVisivel;
        //objeto para imagem da nave
	private Image imagem;
	
        //lista dinamica de misseis
	private List<Missel> misseis;
	
        //construtor
	public Nave(){
		//puxa a imagem
		ImageIcon referencia = new ImageIcon("res\\nave.gif");
		imagem = referencia.getImage();
		//define largura e altura  para eles pegar a da nave
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
		// instancia o missel
		misseis = new ArrayList<Missel>();
		//cordenadas da nave
		this.x = 100;
		this.y = 100;
		
	}
	// metodo para dar movimento na nave 
	public void mexer(){
                //cordenada soma a variavel para o movimento
		x += dx; // 1 e 462
		y += dy; // 1 e 340
                
                //não deixa a nave passar das bordas
		if(this.x < 1){
			x = 1;
		}
		
		if(this.x > 462){
			x = 462;
		}
		
		if(this.y < 1){
			y = 1;
		}

		if(this.y > 340){
			y = 340;
		}
		
	}
	
	public List<Missel> getMisseis() {
		return misseis;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {	
		return y;
	}
	
	public Image getImagem() {
		return imagem;
	}

	public boolean isVisivel() {
		return isVisivel;
	}
	
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	//metodo que aciona um novo missel na lista que a nave vai ter
	public void atira(){
            //estancia um novo objeto colocando a cordenada da frente da nave
		this.misseis.add(new Missel(x+largura, y + altura/2 ));
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
	
	// metodo KeyPresses quando aperta a tecla sempre movendo
	public void keyPressed(KeyEvent tecla){
		// variavel que recebe o codigo da tabela ASCII
		int codigo = tecla.getKeyCode();
		// atira no espaço
		if(codigo == KeyEvent.VK_SPACE){
			atira();
		}
                //diminue o dy ou seja a nave sobe y altura
		if(codigo == KeyEvent.VK_W){
			dy = -3;
		}
		// soma o dy para subir
		if(codigo == KeyEvent.VK_S){
			dy = 3;
		}
		// x largura
		if(codigo == KeyEvent.VK_A){
			dx = -3;
		}
		
		if(codigo == KeyEvent.VK_D){
			dx = 3;
		}
		
	}
	// metodo para anular o valor para não deixar o objeto se movendo sem parar
        //ou seja ela movimenta e pata
	public void keyReleased(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_W){
			dy = 0;
		}
 
		if(codigo == KeyEvent.VK_S){
			dy = 0;
		}
		
		if(codigo == KeyEvent.VK_A){
			dx = 0;
		}
		
		if(codigo == KeyEvent.VK_D){
			dx = 0;
		}
		
	}
	
	
}

