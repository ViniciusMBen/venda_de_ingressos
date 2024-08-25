
package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import controller.*;

public class FrmInicio extends JFrame implements ActionListener {
	private JLabel lblSelecione;
	private JButton btnCli, btnOrg;
	private Container c = getContentPane();
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

	public FrmInicio() {
		Container c = getContentPane();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int) (screen.width * 0.4), (int) (screen.height * 0.4));
		setTitle("Inicio");
		c.setLayout(null);
		setLocationRelativeTo(null); // Centraliza
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Criando posições "âncora" na tela, Y é a distância do topo, x, x1, x2... são
		// a distância da esquerda e
		// locais de colunas
		int y = 40, x = 100, x2 = 260, centerX = getWidth() / 2, centerY = getHeight() / 2;
		lblSelecione = criarLabel("Iniciar Como", centerX - 60, y);

		y += 100;

		// largura botão = 140
		btnCli = criarButton("Cliente", centerX - 150, y);
		btnOrg = criarButton("Organizador", centerX + 10, y);

		setVisible(true);
	}

	public JButton criarButton(String text, int x, int y) {
		JButton btn = new JButton(text);
		btn.setLocation(x, y);
		btn.setSize(140, 50);
		btn.setFont(new Font("Helvetica", Font.BOLD, 18));
		add(btn);
		btn.addActionListener(this);
		return btn;
	}

	public JLabel criarLabel(String text, int x, int y) {
		JLabel lbl = new JLabel(text);
		lbl.setSize(120, 60);
		lbl.setLocation(x, y);
		lbl.setForeground(Color.BLACK);
		lbl.setFont(new Font("Helvetica", Font.BOLD, 18));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setVerticalAlignment(SwingConstants.CENTER);
		add(lbl);
		return lbl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCli) {
			AppEventos.abreLogin(this, "Cliente");
		}
		if (e.getSource() == btnOrg) {
			AppEventos.abreLogin(this, "Organizador");
		}

	}

	public void initFrame() {

		c = getContentPane();
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1000, 600);// Tamanho da Janela
		setTitle("Início"); // Título da janela
		c.setLayout(null);
		setLocationRelativeTo(null); // Centraliza
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}