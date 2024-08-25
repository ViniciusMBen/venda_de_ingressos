package view;

import javax.swing.*;
import model.beans.*;
import controller.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class FrmCadastroEvento extends JFrame implements ActionListener {

    private Container c;
    private Dimension screen;
    private JLabel lblNomeEv, lblLocal, lblDuracao, lblDataInicio, lblDataFim, lblHoraInicio, lblHoraFim,
            lblIdadeMinima, lblValor;
    private JTextField txtNomeEv, txtDuracao, txtDataInicio, txtDataFim, txtHoraInicio, txtHoraFim, txtIdadeMinima,
            txtValor;
    private JComboBox cbxLocal;
    private JButton btnCadastrar, btnCancelar;

    public FrmCadastroEvento() {
        initFrame();

        int xlbl = vw(0.05), xtxt = vw(0.18), x2lbl = vw(0.48), x2txt = vw(0.60), y = 60,
                centroX = (int) (getWidth() / 2);
        lblLocal = criarLabel("Local", xlbl, y);
        cbxLocal = criarComboBox(carregarLocais(), xtxt, y);
        cbxLocal.addActionListener(this);

        y += 100;
        lblNomeEv = criarLabel("Nome do Evento", xlbl, y);
        txtNomeEv = criarTxtField(xtxt, y);
        lblDuracao = criarLabel(" Duração", x2lbl, y);
        txtDuracao = criarTxtField(x2txt, y);
        y += 100;
        lblDataInicio = criarLabel("Data de Início", xlbl, y);
        txtDataInicio = criarTxtField(xtxt, y);
        lblDataFim = criarLabel(" Data Final", x2lbl, y);
        txtDataFim = criarTxtField(x2txt, y);
        y += 100;
        lblHoraInicio = criarLabel("Hora de Início", xlbl, y);
        txtHoraInicio = criarTxtField(xtxt, y);
        lblHoraFim = criarLabel(" Hora Final", x2lbl, y);
        txtHoraFim = criarTxtField(x2txt, y);
        y += 100;
        lblIdadeMinima = criarLabel("Idade Mínima", xlbl, y);
        txtIdadeMinima = criarTxtField(xtxt, y);
        lblValor = criarLabel(" Preço", x2lbl, y);
        txtValor = criarTxtField(x2txt, y);
        y += 100;
        btnCadastrar = criarButton("Cadastrar", centroX - 150, y);
        btnCancelar = criarButton("Voltar", centroX + 10, y);
        setVisible(true);
    }

    private String[] carregarLocais() {
        ArrayList<LocalDeEvento> listaLocais = LocalDeEventoController.getListaLocais();
        String[] nomes = new String[listaLocais.size()];
        for (int i = 0; i < listaLocais.size(); i++) {
            nomes[i] = listaLocais.get(i).getId() + "-" + listaLocais.get(i).getNome();
        }
        return nomes;
    }

    public JComboBox<String> criarComboBox(String[] dados, int x, int y) {
        JComboBox<String> cbx = new JComboBox<>(dados);
        cbx.setSize(vw(0.30), 50);
        cbx.setLocation(x, y);
        cbx.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(cbx);
        cbx.setVisible(true);
        return cbx;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCadastrar) {
            String nome = txtNomeEv.getText();
            int local = Integer.parseInt(cbxLocal.getSelectedItem().toString().split("-")[0]);
            String dataInicio = txtDataInicio.getText(), dataFim = txtDataFim.getText();
            String horaInicio = txtHoraInicio.getText(), horaFim = txtHoraFim.getText();
            String duracao = txtDuracao.getText();
            int idadeMin = Integer.parseInt(txtIdadeMinima.getText());
            float preco = Float.parseFloat(txtValor.getText());

            if (EventoController.addEvento(nome, local, duracao, dataInicio, dataFim, horaInicio, horaFim, idadeMin, preco)) {
                txtNomeEv.setText("");
                cbxLocal.setSelectedIndex(0);
                txtDuracao.setText("");
                txtDataFim.setText("");
                txtDataInicio.setText("");
                txtHoraFim.setText("");
                txtHoraInicio.setText("");
                txtIdadeMinima.setText("");
                txtValor.setText("");
                JOptionPane.showMessageDialog(null, "Evento Cadastrado");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar evento");
            }

        }
        if (e.getSource() == btnCancelar) {
            AppEventos.abreEventosO(this);
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
        setSize(screen.width, screen.height);// Tamanho da Janela
        setTitle("Cadastrar Evento"); // Título da janela
        c.setLayout(null);
        setLocationRelativeTo(null); // Centraliza
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
