package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.AppEventos;

public abstract class Componentes extends JFrame implements ActionListener {

    Container c;
    Dimension screen;
    //Construtor das janelas

    public Componentes() {
        initFrame();
        //Adicionar componentes aqui

        setVisible(true);
    }

    /**
     * Retorna porcentagem da largura da tela
     *
     * @param value valor de 0 a 1 representando a porcentagem, 0.6 = 60%, 0.25
     * = 25%
     * @return Porcentagem da largura do frame
     */
    private int vw(double value) {
        return (int) (getWidth() * value);
    }

    /**
     * Retorna porcentagem da altura da tela
     *
     * @param value valor de 0 a 1 representando a porcentagem, 0.6 = 60%, 0.25
     * = 25%
     * @return Porcentagem da altura do frame
     */
    private int vh(double value) {
        return (int) (getHeight() * value);
    }

    public void initFrame() {
        c = getContentPane();
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width, screen.height);//Tamanho da Janela
        setTitle("Título"); // Título da janela
        c.setLayout(null);
        setLocationRelativeTo(null); // Centraliza
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Cria uma JTextField padrão com largura 73% do tamanho da janela e altura 50 com tratamento de
     * input
     *
     * @param x Posição X
     * @param y Posição Y
     * @return JTextField
     */
    public JTextField criarTxtField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setSize(vw(0.73), 50);
        txt.setLocation(x, y);
        txt.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(txt);
        txt.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {

            }

            public void keyTyped(KeyEvent e) {
                JTextField tf = (JTextField) e.getSource();
                String txt = tf.getText();
                //Tratamento de tamanho do texto inserido no JTextField
                if (tf == new JTextField() && txt.length() > 49);
            }

            public void keyPressed(KeyEvent e) {

            }
        });
        return txt;
    }

    /**
     * Cria um JPasswordField padrão com mesmo tamanho da JTextField, 73% do tamanho da janela de
     * largura e 50 de altura, Possui tratamento de input
     *
     * @param x Posição X
     * @param y Posição Y
     * @return JPasswordField
     */
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
                //Tratamento do tamanho da senha
                if (pass.length() < 8) {
                    //lblWarnPass.setVisible(true);//torna a label de aviso da senha visível
                } else {
                    //lblWarnPass.setVisible(false);
                }
                //String passPrincipal = String.valueOf(pwdPass.getPassword());
                /*//Verificação de senha e confirmação de senha iguais
                if (pass.equals(passPrincipal)) {
                    lblWarnPass2.setVisible(false);
                } else {
                    lblWarnPass2.setVisible(true);
                }*/

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

    /**
     * Cria uma Checkbox padrão de largura 180 e altura 50
     *
     * @param texto Texto da checkbox
     * @param x Posição X
     * @param y Posição Y
     * @return JCheckBox
     */
    public JCheckBox criarCheckBox(String texto, int x, int y) {
        JCheckBox chk = new JCheckBox(texto);
        chk.setLocation(x, y);
        chk.setSize(180, 50);
        chk.setFont(new Font("Helvetica", Font.PLAIN, 18));
        chk.setHorizontalAlignment(SwingConstants.LEFT);
        chk.setVerticalAlignment(SwingConstants.CENTER);
        add(chk);
        chk.addActionListener(this);
        return chk;
    }

    /**
     * Cria um botão padrão de larguta 140 e altura 50
     *
     * @param text Texto do botão
     * @param x Posição X
     * @param y Posição Y
     * @return JButton
     */
    public JButton criarButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setLocation(x, y);
        btn.setSize(140, 50);
        btn.setFont(new Font("Helvetica", Font.BOLD, 18));
        add(btn);
        btn.addActionListener(this);
        return btn;
    }

    /**
     * Cria uma label padrão alinhada à esquerda, centralizada verticalmente
     *
     * @param text Texto da label
     * @param x Posição x
     * @param y Posição y
     * @return JLabel
     */
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

    /**
     * Label de aviso que aparece abaixo de um campo JTextField ou
     * JPasswordField Sua largura pode ser a mesma da JTextField e seu x ser o
     * mesmo da JTextField
     *
     * @param text Aviso
     * @param x Posição x
     * @param y Posição y
     * @return JLabel
     */
    public JLabel criarWarningLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setSize(vw(0.73), 60);
        lbl.setLocation(x, y);
        lbl.setForeground(Color.RED);
        lbl.setFont(new Font("Helvetica", Font.PLAIN, 14));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setVerticalAlignment(SwingConstants.CENTER);
        add(lbl);
        lbl.setVisible(false);
        return lbl;
    }

    /**
     * Cria uma JComboBox padrão com largura de 40% da janela e altura 50
     *
     * @param dados Lista de dados da combobox
     * @param x Posição X
     * @param y Posição Y
     * @return JComboBox
     */
    public JComboBox<String> criarComboBox(String[] dados, int x, int y) {
        JComboBox<String> cbx = new JComboBox<>(dados);
        cbx.setSize(vw(0.4), 50);
        cbx.setLocation(x, y);
        cbx.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(cbx);
        cbx.setVisible(true);
        return cbx;
    }

    /**
     * Cria um JScrollPane para o componente passado como parâmetro
     *
     * @param componente Componente que receberá o scroll
     * @return JScrollPane
     */
    public JScrollPane criarScrollPane(JComponent componente) {
        JScrollPane scroll = new JScrollPane(componente);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(scroll);
        return scroll;
    }

    /**
     * Cria um JPanel
     *
     * @param x
     * @param y
     * @return
     */
    public JPanel criarPanel(int x, int y) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setLocation(x, y);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if(e.getSource() == botaoX){
        // Faça algo
        //}
    }

}
