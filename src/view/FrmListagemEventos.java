package view;

import model.beans.Evento;
import javax.swing.*;

import controller.AppEventos;
import controller.EventoController;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class FrmListagemEventos extends JFrame implements ActionListener {

    private JPanel panelPrincipal, panelTop;
    private JScrollPane scroll;
    private JButton btnVoltar;
    private Container c;
    private Dimension screen;

    public FrmListagemEventos() {
        initFrame();
        ArrayList<Evento> eventos = EventoController.getListaEventos();
        panelPrincipal = criarPanel();
        carregarEventos();
        
        btnVoltar = criarButton("Voltar");
        panelTop = criarPanel();
        panelTop.setLayout(new BorderLayout());
        panelTop.setSize(new Dimension(vw(0.9), vh(0.10)));
        panelTop.setMaximumSize(new Dimension(vw(0.9), vh(0.10)));
        panelTop.add(btnVoltar, BorderLayout.WEST);
        add(panelTop);
        scroll = criarScrollPane(panelPrincipal);

        setVisible(true);
    }

    private void carregarEventos() {
        ArrayList<Evento> eventos = EventoController.getListaEventos();
        for (Evento evento : eventos) {
            JPanel itemLista = criarItemLista(evento);
            panelPrincipal.add(itemLista);
        }
        panelPrincipal.revalidate(); // Força a atualização do layout do JPanel principal
    }

    private JButton criarButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(140, 50));
        btn.setFont(new Font("Helvetica", Font.BOLD, 18));
        btn.addActionListener(this);
        return btn;
    }

    private JScrollPane criarScrollPane(JComponent componente) {
        JScrollPane scroll = new JScrollPane(componente, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setEnabled(true);
        scroll.setPreferredSize(new Dimension(vw(0.9), vh(0.75)));
        add(scroll);
        return scroll;
    }

    private JPanel criarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    private JPanel criarItemLista(Evento e) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 4, 2, 2));

        JButton btnVerEvento;
        JLabel nomeEvento = new JLabel(e.getNomeEvento());
        nomeEvento.setFont(new Font("Helvetica", Font.BOLD, 24));
        nomeEvento.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel preco = new JLabel("R$ " + Float.toString(e.getValorIngresso()));
        preco.setHorizontalAlignment(SwingConstants.CENTER);
        preco.setFont(new Font("Helvetica", Font.BOLD, 30));
        JLabel local = new JLabel("Local: " + e.getNome());
        local.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel duracao = new JLabel("Duração: " + e.getDuracao());
        duracao.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel idadeMin;
        if (e.getIdadeMin() == 0) {
            idadeMin = new JLabel("Para todas as idades");
        } else {
            idadeMin = new JLabel("Idade mínima " + Integer.toString(e.getIdadeMin()) + " anos");
        }
        idadeMin.setFont(new Font("Helvetica", Font.PLAIN, 18));
        idadeMin.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel data = new JLabel(e.getDataInicio());
        data.setHorizontalAlignment(SwingConstants.CENTER);

        btnVerEvento = new JButton("Ver");
        btnVerEvento.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                AppEventos.abreEvento(e);
                setVisible(false);
            }

        });

        panel.add(nomeEvento);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(idadeMin);
        panel.add(preco);
        panel.add(new JLabel());
        panel.add(btnVerEvento);
        panel.add(local);
        panel.add(data);
        panel.add(duracao);
        panel.add(new JLabel());

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setPreferredSize(new Dimension(vw(0.9), 160));
        panel.setMaximumSize(new Dimension(vw(0.9), 160));
        return panel;
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

    private void initFrame() {
        c = getContentPane();
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width, screen.height);//Tamanho da Janela
        setTitle("Lista de Eventos"); // Título da janela
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        setLocationRelativeTo(null); // Centraliza
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVoltar) {
            AppEventos.abreLogin(this, "Cliente");
        }
    }
}
