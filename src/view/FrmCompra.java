package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import model.beans.*;
import controller.*;

public class FrmCompra extends JFrame implements ActionListener {

    private Container c;
    private Dimension screen;
    private Evento evento;
    private JComboBox cbxAssento;
    private JButton btnComprar, btnCancelar;
    private JLabel lblNomeEvento, lblPreco, lblAssento;
    private String[] listaAssentos;

    public FrmCompra(Evento evento) {
        this.evento = evento;
        initFrame();
        int y = 120, x = vw(0.15), x2 = vw(0.55), centerX = getWidth() / 2, centerY = getHeight() / 2;
        lblNomeEvento = criarLabel(evento.getNomeEvento(), centerX - vw(0.15), y);
        lblNomeEvento.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomeEvento.setSize(vw(0.30), 60);
        lblNomeEvento.setFont(new Font("Helvetica", Font.BOLD, 28));
        y += 120;
        lblPreco = criarLabel("R$ " + Float.toString(evento.getValorIngresso()), x+100, y);
        lblPreco.setFont(new Font("Helvetica", Font.BOLD, 24));
        y += 120;
        if (evento.getColunas() > 0) {
            carregaListaAssentos();

            lblAssento = criarLabel("Selecione o Assento", x, y);
            lblAssento.setSize(vw(0.3), 60);
            cbxAssento = criarComboBox(listaAssentos, x2, y);
        }
        y += 120;
        btnComprar = criarButton("Comprar", centerX - 150, y);
        btnCancelar = criarButton("Cancelar", centerX + 10, y);
        setVisible(true);
    }

    private void carregaListaAssentos() {
        int quantAssentos = evento.getFileiras() * evento.getColunas();
        listaAssentos = new String[quantAssentos];
        int indexLista = 0;
        for (int c = 1; c <= evento.getColunas(); c++) {
            for (int f = 1; f <= evento.getFileiras(); f++) {
                listaAssentos[indexLista] = "Fileira " + f + " Coluna " + c;
                indexLista++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnComprar) {
            int col = 0, fil = 0;
            String[] meiosPag = {"PIX", "Débito", "Crédito"};
            String meio = JOptionPane.showInputDialog(null, "Selecione a Forma de Pagamento", "PagTudo", JOptionPane.PLAIN_MESSAGE, null, meiosPag, meiosPag[0]).toString();
            JOptionPane.showInputDialog(null, "Digite seu CPF");
            if(meio.equals("PIX")){
                JOptionPane.showMessageDialog(null, "Código pix para realizar o pagameno:\nPIXFEFERTRFA6FD9UGG7FHJ0G9FXFDS8909890ADS8.pagTudo");
            }else{
                JOptionPane.showInputDialog(null, "Insira os dados do cartão");
            }
            if (evento.getColunas() > 0) {
                fil = Integer.parseInt(cbxAssento.getSelectedItem().toString().split(" ")[1]);
                col = Integer.parseInt(cbxAssento.getSelectedItem().toString().split(" ")[3]);

                if (AssentoController.inserirAssento(fil, col, evento.getId())
                        && IngressoController.inserirIngressoComAssento(evento, col, fil)) {
                    JOptionPane.showMessageDialog(null, "Pagamento Confirmado, seu ingresso chegará em seu E-mail");
                }
            } else {
                if (IngressoController.inserirIngressoSemAssento(evento)) {
                    JOptionPane.showMessageDialog(null, "Pagamento Confirmado, seu ingresso chegará em seu E-mail");
                }
            }
            AppEventos.abreEventos(this);

        }
        if (e.getSource() == btnCancelar) {
            AppEventos.abreEvento(evento);
        }

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

    public JComboBox<String> criarComboBox(String[] dados, int x, int y) {
        JComboBox<String> cbx = new JComboBox<>(dados);
        cbx.setSize(vw(0.4), 50);
        cbx.setLocation(x, y);
        cbx.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(cbx);
        cbx.setVisible(true);
        return cbx;
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
        setTitle("Comprar Ingresso"); // Título da janela
        c.setLayout(null);
        setLocationRelativeTo(null); // Centraliza
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
