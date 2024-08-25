package view;

import javax.swing.*;

import controller.*;
import model.beans.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class FrmListagemLocaisO extends JFrame implements ActionListener {

    private JPanel panelPrincipal, panelTop;
    private JScrollPane scroll;
    private JButton btnNovoLc, btnIrEv;
    private Container c;
    private Dimension screen;

    public FrmListagemLocaisO() {
        initFrame();
        panelPrincipal = criarPanel();
        carregarLocais();

        btnNovoLc = criarButton("Novo Local");
        btnIrEv = criarButton("Ver Eventos");
        panelTop = criarPanel();
        panelTop.setLayout(new BorderLayout());
        panelTop.setSize(new Dimension(vw(0.9), vh(0.10)));
        panelTop.setMaximumSize(new Dimension(vw(0.9), vh(0.10)));
        panelTop.add(btnNovoLc, BorderLayout.EAST);
        panelTop.add(btnIrEv, BorderLayout.WEST);
        add(panelTop);
        scroll = criarScrollPane(panelPrincipal);

        setVisible(true);
    }

    private void carregarLocais() {
        ArrayList<LocalDeEvento> locais = LocalDeEventoController.getListaLocais();
        for (LocalDeEvento local : locais) {
            JPanel itemLista = criarItemLista(local);
            panelPrincipal.add(itemLista);
        }
        panelPrincipal.revalidate(); // Força a atualização do layout do JPanel principal
    }

    private JButton criarButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(200, 50));
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

    private JPanel criarItemLista(LocalDeEvento lc) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 4, 2, 2));

        JButton btnEditarLocal, btnExcluirLocal;
        JLabel nomeLocal = new JLabel(lc.getNome());
        JLabel logradouro = new JLabel(lc.getLogradouro() + ", " + lc.getNumero());
        JLabel capacidade = new JLabel("Capacidade: " + Integer.toString(lc.getCapacidade()));
        nomeLocal.setFont(new Font("Helvetica", Font.BOLD, 24));
        nomeLocal.setHorizontalAlignment(SwingConstants.CENTER);
        logradouro.setHorizontalAlignment(SwingConstants.CENTER);
        capacidade.setHorizontalAlignment(SwingConstants.CENTER);

        btnEditarLocal = new JButton("Editar");
        btnEditarLocal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                AppEventos.abreEditLocal(lc);
                setVisible(false);
            }

        });
        btnExcluirLocal = new JButton("Excluir");
        btnExcluirLocal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o local " + lc.getNome() + "?", null, JOptionPane.YES_NO_OPTION) == 0) {
                    LocalDeEvento lcExcluido = LocalDeEventoController.excluiLocal(lc);
                    reloadFrame();
                }

            }
        });
        panel.add(nomeLocal);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(logradouro);
        panel.add(new JLabel());
        panel.add(btnEditarLocal);
        panel.add(btnExcluirLocal);
        panel.add(capacidade);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setPreferredSize(new Dimension(vw(0.9), 160));
        panel.setMaximumSize(new Dimension(vw(0.9), 160));
        return panel;
    }
    private void reloadFrame(){
        AppEventos.abreLocais(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIrEv) {
            AppEventos.abreEventosO(this);
        }
        if (e.getSource() == btnNovoLc) {
            AppEventos.abreCadLocal(this);
        }

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
        setTitle("Lista de Locais de Evento"); // Título da janela
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        setLocationRelativeTo(null); // Centraliza
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
