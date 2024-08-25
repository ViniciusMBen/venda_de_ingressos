package view;

import javax.swing.*;

import controller.AppEventos;
import controller.UsuarioController;

import java.awt.event.*;
import java.awt.*;
import model.beans.Usuario;

public class FrmLogin extends JFrame implements ActionListener {

    private JLabel lblMail, lblPass, lblWarnPass;
    private JTextField txtMail;
    private JPasswordField pwdPass;
    private JButton btnLogIn, btnSignUp, btnExit;
    private String tipoUsuario;
    private Container c;
    private Dimension screen;

    public FrmLogin(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        initFrame();
        int y = 200, x = vw(0.05), x2 = vw(0.22), centerX = getWidth() / 2, centerY = getHeight() / 2;
        lblMail = criarLabel("Email", x, y);
        txtMail = criarTxtField(x2, y);

        y += 100;
        lblPass = criarLabel("Senha", x, y);
        lblWarnPass = criarWarningLabel("A senha deve conter pelo menos 8 dígitos.", x2, y + 25);
        pwdPass = criarPassField(x2, y);

        y += 100;
        // largura botão = 140
        btnLogIn = criarButton("Login", centerX - 240, y);
        btnSignUp = criarButton("SignUp", centerX - 70, y);
        btnExit = criarButton("Sair", centerX + 100, y);

        setVisible(true);
    }

    public JTextField criarTxtField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setSize(vw(0.73), 50);
        txt.setLocation(x, y);
        txt.setFont(new Font("Helvetica", Font.PLAIN, 18));
        add(txt);
        txt.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
            }

            public void keyTyped(KeyEvent e) {
                JTextField tf = (JTextField) e.getSource();
                String txt = tf.getText();

                if (tf == txtMail && txt.length() > 69) {
                    tf.setText(txt.substring(0, txt.length() - 1));
                }
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
            }

            public void keyTyped(KeyEvent e) {
                JPasswordField passField = (JPasswordField) e.getSource();
                String pass = String.valueOf(passField.getPassword());
                if (pass.length() > 20) {
                    passField.setText(pass.substring(0, pass.length() - 1));
                }
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
        lbl.setFont(new Font("Helvetica", Font.BOLD, 18));
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
        if (e.getSource() == btnExit) {
            AppEventos.abreInicio(this);
        }
        if (e.getSource() == btnLogIn) {
            String email = txtMail.getText();
            String senha = String.valueOf(pwdPass.getPassword());
            if (!email.equals("") && !senha.equals("")) {
                Usuario u = UsuarioController.fazerLogin(email, senha);
                if (u != null) {
                    if (this.tipoUsuario.equals("Cliente") && !u.isOrganizador()) {
                        AppEventos.abreEventos(this);
                    } else if(this.tipoUsuario.equals("Organizador") && u.isOrganizador()){
                        AppEventos.abreEventosO(this);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Usuário não pode fazer login nesta categoria");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível autenticar");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            }
        }
        if (e.getSource() == btnSignUp) {
            AppEventos.abreCadastro(this, tipoUsuario);

        }
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
        setTitle("Login"); // Título da janela
        c.setLayout(null);
        setLocationRelativeTo(null); // Centraliza
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
