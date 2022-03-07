package Client;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ViewBombermanGame implements KeyListener{
	
	private char move;
	PanelBomberman panel;
	
	JFrame jFrame = new JFrame();
	
	public PanelBomberman getPanel() {
		return panel;
	}


	public void setPanel(PanelBomberman panel) {
		this.panel = panel;
	}


	public JFrame getjFrame() {
		return jFrame;
	}


	public void setjFrame(JFrame jFrame) {
		this.jFrame = jFrame;
	}

	
	public ViewBombermanGame (PanelBomberman panelBG) {
		this.panel=panelBG;
		jFrame.addKeyListener(this);
		jFrame.setContentPane(panel);
		jFrame.setTitle("Bomberman game");
		jFrame.setSize(new Dimension(panel.getSizeX()*50, panel.getSizeY()*50));
		Dimension windowSize=jFrame.getSize();
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint=ge.getCenterPoint();
		int dx=centerPoint.x - windowSize.width/2+(panel.getX()/2);
		int dy=centerPoint.y - windowSize.height/2-(panel.getY()/2);
		jFrame.setLocation(dx,dy);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		setMove((char) e.getKeyCode());
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public char getMove() {
		return move;
	}


	public void setMove(char move) {
		this.move = move;
	}

}
