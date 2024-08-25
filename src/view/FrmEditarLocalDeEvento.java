package view;

import javax.swing.*;

import model.beans.LocalDeEvento;
import controller.*;
import java.awt.event.*;
import java.awt.*;

public class FrmEditarLocalDeEvento extends JFrame implements ActionListener {

    private Container c;
    private Dimension screen;
    private JCheckBox chkTemAssentos;
    private JLabel lblNome, lblLogradouro, lblCep, lblNum, lblCapacidade, lblCol, lblFil;
    private JTextField txtNome, txtLogradouro, txtCep, txtNum, txtCapacidade, txtCol, txtFil;
    private JButton btnEditar, btnCancelar;
    private LocalDeEvento local;

    public FrmEditarLocalDeEvento(LocalDeEvento local) {
        initFrame();
        this.local = local;
        int xlbl = vw(0.05), xtxt = vw(0.18), x2lbl = vw(0.48), x2txt = vw(0.60), y = 60,
                centroX = (int) (getWidth() / 2);
        lblNome = criarLabel("Nome", xlbl, y);
        txtNome = criarTxtField(xtxt, y);
        lblNum = criarLabel(" Número", x2lbl, y);
        txtNum = criarTxtField(x2txt, y);
        y += 100;
        lblLogradouro = criarLabel("Logradouro", xlbl, y);
        txtLogradouro = criarTxtField(xtxt, y);
        lblCep = criarLabel(" CEP", x2lbl, y);
        txtCep = criarTxtField(x2txt, y);
        y += 100;
        lblCapacidade = criarLabel("Capacidade", xlbl, y);
        txtCapacidade = criarTxtField(xtxt, y);
        chkTemAssentos = criarCheckBox("Possui Assentos", x2txt, y);
        y += 100;
        lblFil = criarLabel("Fileiras", xlbl, y);
        txtFil = criarTxtField(xtxt, y);
        lblCol = criarLabel(" Colunas", x2lbl, y);
        txtCol = criarTxtField(x2txt, y);
        y += 100;
        btnEditar = criarButton("Editar", centroX - 150, y);
        btnCancelar = criarButton("Cancelar", centroX + 10, y);
        inicializarValores();
        setVisible(true);
    }

    private void inicializarValores() {
        txtNome.setText(local.getNome());
        txtNum.setText(local.getNumero());
        txtLogradouro.setText(local.getLogradouro());
        txtCep.setText(local.getCEP());
        txtCapacidade.setText(Integer.toString(local.getCapacidade()));
        txtFil.setText(Integer.toString(local.getFileiras()));
        txtCol.setText(Integer.toString(local.getColunas()));
        if (local.getFileiras() > 0 || local.getColunas() > 0) {
            chkTemAssentos.setSelected(true);
        }

    }

    public JLabel criarLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setSize(vw(0.13), 60);
        lbl.setLocation(x, y);
        lbl.setForeground(Color.BLACK);
        lbl.setFont(new Font("Helvetica", Font.BOLD, 18));
        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        lbl.setVerticalAlignment(SwingConstants.CENTER);
        add(lbl);
        return lbl;
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

    public JTextField criarTxtField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setSize(vw(0.30), 50);
        txt.setLocation(x, y);
        txt.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(txt);
        txt.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {

            }

            public void keyTyped(KeyEvent e) {
                JTextField tf = (JTextField) e.getSource();
                String txt = tf.getText();
                // Tratamento de tamanho do texto inserido no JTextField
                if (txt.length() > 69) {
                    tf.setText(txt.substring(0, txt.length() - 1));
                }

            }

            public void keyPressed(KeyEvent e) {

            }
        });
        return txt;
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEditar) {
            String nome = txtNome.getText(),
                    logradouro = txtLogradouro.getText(),
                    cep = txtCep.getText(),
                    num = txtNum.getText();
            int capacidade = Integer.parseInt(txtCapacidade.getText()),
                    col = 0,
                    fil = 0;
            if (chkTemAssentos.isSelected()) {
                col = Integer.parseInt(txtCol.getText());
                fil = Integer.parseInt(txtFil.getText());
            }
            if(LocalDeEventoController.updLocal(local.getId(), nome, logradouro, cep, num, capacidade, fil, col)){
                AppEventos.abreLocais(this);
            }else{
                JOptionPane.showMessageDialog(null,"Falha ao alterar local de evento");
            }
        }
        if (e.getSource() == btnCancelar) {
            AppEventos.abreLocais(this);
        }

    }

    private int vw(double value) {
        return (int) (getWidth() * value);
    }

    private int vh(double value) {
        return (int) (getHeight() * value);
    }

    public void initFrame() {
        c = getContentPane();
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width, screen.height);//Tamanho da Janela
        setTitle("Editar Local de Evento"); // Título da janela
        c.setLayout(null);
        setLocationRelativeTo(null); // Centraliza
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
