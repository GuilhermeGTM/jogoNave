package jogonavetrabalho;




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

// essa class extends o pacote JFrame
public class ContainerDeJanelas extends JFrame{
	
	public ContainerDeJanelas(){
		
		JMenuBar barraMenu = new JMenuBar();
		
		JMenu menu = new JMenu("Menu");

		JMenuItem sobre = new JMenuItem("Sobre");
		sobre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Jogo desenvolvido por Guilherme Timm!", "Informações", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		menu.add(sobre);
		menu.add(new JSeparator());
		menu.add(sair);
		
		barraMenu.add(menu);
		
		setJMenuBar(barraMenu);
		
		// estancia a fase
		add(new Fase());
                //metodo recebe um texto para colocar um titulo
		setTitle("NaveGTM");
                //metodo que fecha a janela no x
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //metodo define o tamanho da janela altura,largura
		setSize(500,420);
                //define para que a janela aparece no centro da tela null aparece no centro
		setLocationRelativeTo(null);
                //metodo que não deixa expandir a tela sem redifinição
		setResizable(false);
                //metodo recebe um valor boolean de verdadeiro e falso  para visualizar a tela
		setVisible(true);
		
	}
	
	
}

