package view;

import controller.AppEventos;
import javax.swing.*;

import controller.*;
import model.beans.Evento;

import java.awt.event.*;
import java.awt.*;

public class FrmEvento extends JFrame implements ActionListener {

    private JLabel lblNomeEv, lblLocal, lblEndereco, lblDuracao, lblDataInicio, lblDataFim, lblHoraInicio, lblHoraFim,
            lblIdadeMinima, lblValor;
    private JButton btnComprar, btnVoltar;
    private Container c;
    private Dimension screen;
    private Evento evento;
    // Visualiza um Evento Selecionado

    public FrmEvento(Evento evento) {
        this.evento = evento;
        initFrame();
        int y = 70, x = vw(0.05), x2 = vw(0.55), centerX = getWidth() / 2, centerY = getHeight() / 2;
        lblNomeEv = criarLabel(evento.getNomeEvento(), centerX - vw(0.175), y);
        lblNomeEv.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomeEv.setFont(new Font("Helvetica", Font.BOLD, 36));
        y += 70;
        lblLocal = criarLabel("Local: " + evento.getNome(), x, y);
        lblEndereco = criarLabel(evento.getLogradouro() + ", " + evento.getNumero() + ", " + evento.getCEP(), x2, y);
        y += 70;
        criarLabel("Início", x, y);
        criarLabel("Fim", x2, y);
        y += 70;
        lblDataInicio = criarLabel(evento.getDataInicio(), x, y);
        lblDataFim = criarLabel(evento.getDataFim(), x2, y);
        y += 70;
        lblHoraInicio = criarLabel(evento.getHoraInicio(), x, y);
        lblHoraFim = criarLabel(evento.getHoraFim(), x2, y);
        y += 70;
        lblDuracao = criarLabel("Duração: " + evento.getDuracao(), x, y);
        lblIdadeMinima = criarLabel("Idade Mínima: " + evento.getIdadeMin(), x2, y);
        y += 70;
        lblValor = criarLabel("R$ " + String.format("%.2f", evento.getValorIngresso()), centerX - vw(0.175), y);
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);
        lblValor.setFont(new Font("Helvetica", Font.BOLD, 28));
        y += 70;
        btnComprar = criarButton("Comprar", centerX - 90, y);
        btnVoltar = criarButton("Voltar", x, vh(0.75));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnComprar) {
            AppEventos.abreCompra(this, this.evento);
        }
        if (e.getSource() == btnVoltar) {
            AppEventos.abreEventos(this);
        }

    }

    public JButton criarButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setLocation(x, y);
        btn.setSize(180, 60);
        btn.setFont(new Font("Helvetica", Font.BOLD, 24));
        add(btn);
        btn.addActionListener(this);
        return btn;
    }

    public JLabel criarLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setSize(vw(0.35), 60);
        lbl.setLocation(x, y);
        lbl.setForeground(Color.BLACK);
        lbl.setFont(new Font("Helvetica", Font.PLAIN, 22));
        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        lbl.setVerticalAlignment(SwingConstants.CENTER);
        add(lbl);
        return lbl;
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
        setTitle("Evento"); // Título da janela
        c.setLayout(null);
        setLocationRelativeTo(null); // Centraliza
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
