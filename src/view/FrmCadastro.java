/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import controller.AppEventos;
import controller.UsuarioController;



public class FrmCadastro extends JFrame implements ActionListener {

    private JLabel lblMail, lblPass, lblPass2, lblWarnPass, lblWarnPass2, lblNome, lblNasc;
    private JTextField txtMail, txtNome, txtNasc;
    private JPasswordField pwdPass, pwdPass2;
    private JButton btnSignUp, btnVoltar;
    private String tipoUsuario;
    private Container c;
    private Dimension screen;
    public FrmCadastro(String tipoUsuario) {
    	this.tipoUsuario = tipoUsuario; // Para Renderizar uma tela cadastro diferente de acordo com o tipo de usuario
        
        initFrame();

        int y = 50, x = vw(0.05), x2 = vw(0.22), centerX = getWidth() / 2, centerY = getHeight() / 2;
        lblMail = criarLabel("Email", x, y);
        txtMail = criarTxtField(x2, y);

        y += 100;
        lblNome = criarLabel("Nome", x, y);
        txtNome = criarTxtField(x2, y);

        y += 100;
        lblPass = criarLabel("Senha", x, y);
        lblWarnPass = criarWarningLabel("A senha deve conter pelo menos 8 dígitos.", x2, y + 25);
        pwdPass = criarPassField(x2, y);

        y += 100;
        lblPass2 = criarLabel("Confirmar Senha", x, y);
        lblWarnPass2 = criarWarningLabel("Não foi possível confirmar a senha, senhas diferentes", x2, y + 25);
        pwdPass2 = criarPassField(x2, y);
        
        if(tipoUsuario.equals("Cliente")) {
        y+= 100;
        lblNasc = criarLabel("Data de Nascimento", x, y);
        txtNasc = criarTxtField(x2, y);
        
        }
        
        y += 100;
        // largura botão = 140
        btnSignUp = criarButton("SignUp", centerX - 150, y);
        btnVoltar = criarButton("Voltar", centerX + 10, y);

        setVisible(true);
    }
    /**
     * Retorna porcentagem da largura da tela
     * @param value valor de 0 a 1 representando a porcentagem, 0.6 = 60%, 0.25 = 25%
     * @return Porcentagem da largura do frame
     */
    private int vw(double value){
        return (int)(getWidth()*value);
    }
    /**
     * Retorna porcentagem da altura da tela
     * @param value valor de 0 a 1 representando a porcentagem, 0.6 = 60%, 0.25 = 25%
     * @return Porcentagem da altura do frame
     */
    private int vh(double value){
        return (int)(getHeight()*value);
    }
    public void initFrame() {
        c = getContentPane();
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width, screen.height);//Tamanho da Janela
        setTitle("Cadastrar"); // Título da janela
        c.setLayout(null);
        setLocationRelativeTo(null); // Centraliza
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public JTextField criarTxtField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setSize(vw(0.73), 50);
        txt.setLocation(x, y);
        txt.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(txt);
        txt.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField tf = (JTextField) e.getSource();
                String txt = tf.getText();
                if(tf == txtNasc && (txt.length() == 2 || txt.length() == 5))
                    tf.setText(txt + "/");
            }

            public void keyTyped(KeyEvent e) {
            	JTextField tf = (JTextField) e.getSource();
                String txt = tf.getText();
                
                if(tf == txtNome && txt.length() > 59 ||
                		tf == txtMail && txt.length() > 69 || tf == txtNasc && txt.length() > 9)
                	tf.setText(txt.substring(0, txt.length()-1));

            }

            public void keyPressed(KeyEvent e) {

            }
        });
        return txt;
    }

    public JPasswordField criarPassField(int x, int y) {
        JPasswordField pwd = new JPasswordField();
        pwd.setSize(vw(0.73), 50);
        pwd.setLocation(x, y);
        pwd.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(pwd);
        pwd.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JPasswordField passField = (JPasswordField) e.getSource();
                String pass = String.valueOf(passField.getPassword());
                if (pass.length() < 8) {
                    lblWarnPass.setVisible(true);
                } else {
                    lblWarnPass.setVisible(false);
                }
                String passPrincipal = String.valueOf(pwdPass.getPassword());

                if (pass.equals(passPrincipal)) {
                    lblWarnPass2.setVisible(false);
                } else {
                    lblWarnPass2.setVisible(true);
                }
                

            }

            public void keyTyped(KeyEvent e) {
                JPasswordField passField = (JPasswordField) e.getSource();
                String pass = String.valueOf(passField.getPassword());
                if(pass.length() > 20)
                	passField.setText(pass.substring(0, pass.length()-1));
            }

            public void keyPressed(KeyEvent e) {

            }
        });
        return pwd;
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
        lbl.setSize(vw(0.15), 60);
        lbl.setLocation(x, y);
        lbl.setForeground(Color.BLACK);
        lbl.setFont(new Font("Helvetica", Font.BOLD, 16));
        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        lbl.setVerticalAlignment(SwingConstants.CENTER);
        add(lbl);
        return lbl;
    }

    public JLabel criarWarningLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setSize(vw(0.73), 80);
        lbl.setLocation(x, y);
        lbl.setForeground(Color.RED);
        lbl.setFont(new Font("Helvetica", Font.PLAIN, 14));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setVerticalAlignment(SwingConstants.CENTER);
        add(lbl);
        lbl.setVisible(false);
        return lbl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnVoltar){
            AppEventos.abreLogin(this, tipoUsuario);
        }
        if (e.getSource() == btnSignUp) {
    		String nome = txtNome.getText();
    		String email = txtMail.getText();
    		String senha = String.valueOf(pwdPass.getPassword());
                boolean organizador = tipoUsuario.equals("Organizador");
                String dataNascimento = "";
                if(!organizador)
                    dataNascimento = txtNasc.getText();
        	if(UsuarioController.fazerCadastro(nome, email, senha, organizador, dataNascimento)) {
                    JOptionPane.showMessageDialog(null,"Cadastrado com sucesso");
                    AppEventos.abreLogin(this, tipoUsuario);
        	}
        	else{
                    JOptionPane.showMessageDialog(null,"Falha ao realizar o cadastro");
        	}
        }

    }
}
