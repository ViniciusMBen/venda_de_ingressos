package view;

import javax.swing.*;

import controller.AppEventos;
import controller.EventoController;
import controller.LocalDeEventoController;
import model.beans.Evento;
import model.beans.LocalDeEvento;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class FrmEditarEvento extends JFrame implements ActionListener {

    private Container c;
    private Dimension screen;
    private Evento ev;
    private JLabel lblNomeEv, lblLocal, lblDuracao, lblDataInicio, lblDataFim, lblHoraInicio, lblHoraFim,
            lblIdadeMinima, lblValor;
    private JTextField txtNomeEv, txtDuracao, txtDataInicio, txtDataFim, txtHoraInicio, txtHoraFim, txtIdadeMinima,
            txtValor;
    private JComboBox cbxLocal;
    private JButton btnEditar, btnCancelar;

    public FrmEditarEvento(Evento evento) {
        ev = evento;
        initFrame();
        // Posições da janela
        // 5% jlabels 15% Jtextfield/Jcombobox 50% vazio 55% Jlabels 65% JTextFields
        int xlbl = vw(0.05), xtxt = vw(0.18), x2lbl = vw(0.48), x2txt = vw(0.60), y = 100,
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
        btnEditar = criarButton("Editar", centroX - 150, y);
        btnCancelar = criarButton("Cancelar", centroX + 10, y);
        inicializaCampos();
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

    private void inicializaCampos() {
        txtNomeEv.setText(ev.getNomeEvento());
        cbxLocal.setSelectedItem(ev.getId() + "-" + ev.getNome());
        txtDataInicio.setText(ev.getDataInicio());
        txtDataFim.setText(ev.getDataFim());
        txtHoraInicio.setText(ev.getHoraInicio());
        txtHoraFim.setText(ev.getHoraFim());
        txtDuracao.setText(ev.getDuracao());
        txtIdadeMinima.setText(Integer.toString(ev.getIdadeMin()));
        txtValor.setText(Float.toString(ev.getValorIngresso()));
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
        if (e.getSource() == btnEditar) {
            String nome = txtNomeEv.getText();
            int local = Integer.parseInt(cbxLocal.getSelectedItem().toString().split("-")[0]);
            String dataInicio = txtDataInicio.getText(), dataFim = txtDataFim.getText();
            String horaInicio = txtHoraInicio.getText(), horaFim = txtHoraFim.getText();
            String duracao = txtDuracao.getText();
            int idadeMin = Integer.parseInt(txtIdadeMinima.getText());
            float preco = Float.parseFloat(txtValor.getText());
            if (EventoController.updEvento(ev.getEId(), nome, local, duracao, dataInicio, dataFim, horaInicio, horaFim, idadeMin,
                    preco)) {
                AppEventos.abreEventosO(this);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao alterar Evento");
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
        setTitle("Editar Evento"); // Título da janela
        c.setLayout(null);
        setLocationRelativeTo(null); // Centraliza
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
