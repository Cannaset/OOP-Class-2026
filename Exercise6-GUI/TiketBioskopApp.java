import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 1. TOP-LEVEL CONTAINER: Mewarisi JFrame
public class TiketBioskopApp extends JFrame implements ActionListener {

    // Definisi Rentang Palet Warna Hitam-Maroon (Nuansa Gradasi)
    private final Color BG_ONYX = new Color(22, 18, 20);        // #161214 - Hitam keunguan/kemerahan lembut
    private final Color PANEL_RAISIN = new Color(38, 25, 29);   // #26191D - Abu-abu gelap bernuansa maroon
    private final Color ACCENT_BURGUNDY = new Color(69, 24, 34);// #451822 - Maroon gelap untuk aksen & header
    private final Color BTN_WINE = new Color(122, 31, 52);      // #7A1F34 - Maroon medium untuk tombol utama
    private final Color SEAT_SELECTED = new Color(168, 48, 75); // #A8304B - Crimson terang saat kursi dipilih
    private final Color TEXT_ROSE = new Color(230, 206, 212);   // #E6CED4 - Putih keabu-abuan bernuansa rose

    // 2. ATOMIC COMPONENTS (Enkapsulasi atribut private)
    private JTextField txtNama;
    private JComboBox<String> cbFilm;
    private JRadioButton rbReguler, rbVIP;
    private ButtonGroup bgTipeKursi;
    private JToggleButton[] btnKursi;
    private JTextArea areaStruk;
    private JButton btnPesan, btnReset;
    private JLabel lblTotalHarga;

    private final int HARGA_REGULER = 45000;
    private final int HARGA_VIP = 75000;

    public TiketBioskopApp() {
        // Konfigurasi JFrame (Top-Level Container)[cite: 1]
        setTitle("CinemaX - Pemesanan Tiket Bioskop");
        setSize(750, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));
        getContentPane().setBackground(BG_ONYX);

        // =================================================================
        // 3. INTERMEDIATE CONTAINERS (JPanel & JScrollPane)
        // =================================================================

        // A. Panel Header (Bagian Atas)
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(ACCENT_BURGUNDY);
        panelHeader.setBorder(new EmptyBorder(12, 15, 12, 15));
        JLabel lblJudul = new JLabel("CINEMAX EXCLUSIVE BOOKING");
        lblJudul.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblJudul.setForeground(TEXT_ROSE);
        panelHeader.add(lblJudul);
        add(panelHeader, BorderLayout.NORTH);

        // B. Panel Tengah (Membagi Form Kiri dan Struk Kanan)
        JPanel panelTengah = new JPanel(new GridLayout(1, 2, 12, 0));
        panelTengah.setBackground(BG_ONYX);
        panelTengah.setBorder(new EmptyBorder(0, 12, 0, 12));

        // B1. Sub-Panel Kiri (Input Data & Pilih Kursi)
        JPanel panelInputKiri = new JPanel(new BorderLayout(0, 12));
        panelInputKiri.setBackground(BG_ONYX);

        // Intermediate Container: Panel Form Data
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 8, 10));
        panelForm.setBackground(PANEL_RAISIN);
        panelForm.setBorder(buatBorderJudul("Data Pemesanan"));

        JLabel lblNama = buatLabel("Nama Pemesan:");
        txtNama = new JTextField();
        txtNama.setBackground(BG_ONYX);
        txtNama.setForeground(TEXT_ROSE);
        txtNama.setCaretColor(TEXT_ROSE);
        txtNama.setBorder(new LineBorder(ACCENT_BURGUNDY, 1));

        JLabel lblFilm = buatLabel("Pilih Film:");
        String[] daftarFilm = {"Dune: Part Two", "Oppenheimer", "Interstellar", "The Batman"};
        cbFilm = new JComboBox<>(daftarFilm);
        cbFilm.setBackground(BG_ONYX);
        cbFilm.setForeground(TEXT_ROSE);

        JLabel lblTipe = buatLabel("Kelas Studio:");
        rbReguler = new JRadioButton("Reguler (45rb)", true);
        rbVIP = new JRadioButton("VIP (75rb)");
        aturGayaRadio(rbReguler);
        aturGayaRadio(rbVIP);

        bgTipeKursi = new ButtonGroup();
        bgTipeKursi.add(rbReguler);
        bgTipeKursi.add(rbVIP);

        panelForm.add(lblNama);
        panelForm.add(txtNama);
        panelForm.add(lblFilm);
        panelForm.add(cbFilm);
        panelForm.add(lblTipe);
        panelForm.add(rbReguler);
        panelForm.add(new JLabel("")); // Spacer kosong
        panelForm.add(rbVIP);

        // Intermediate Container: Panel Pemilihan Kursi
        JPanel panelKursi = new JPanel(new GridLayout(2, 4, 8, 8));
        panelKursi.setBackground(PANEL_RAISIN);
        panelKursi.setBorder(buatBorderJudul("Pilih Nomor Kursi (Layar di Atas)"));

        String[] kodeKursi = {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4"};
        btnKursi = new JToggleButton[kodeKursi.length];

        for (int i = 0; i < kodeKursi.length; i++) {
            btnKursi[i] = new JToggleButton(kodeKursi[i]);
            btnKursi[i].setBackground(ACCENT_BURGUNDY);
            btnKursi[i].setForeground(TEXT_ROSE);
            btnKursi[i].setFocusPainted(false);
            btnKursi[i].setBorder(new LineBorder(BTN_WINE, 1));
            // Event Handling untuk setiap klik kursi[cite: 1]
            btnKursi[i].addActionListener(this);
            panelKursi.add(btnKursi[i]);
        }

        panelInputKiri.add(panelForm, BorderLayout.NORTH);
        panelInputKiri.add(panelKursi, BorderLayout.CENTER);

        // B2. Sub-Panel Kanan (Ringkasan Struk dengan JScrollPane)
        JPanel panelStruk = new JPanel(new BorderLayout());
        panelStruk.setBackground(PANEL_RAISIN);
        panelStruk.setBorder(buatBorderJudul("Ringkasan Tiket"));

        areaStruk = new JTextArea();
        areaStruk.setEditable(false);
        areaStruk.setBackground(BG_ONYX);
        areaStruk.setForeground(TEXT_ROSE);
        areaStruk.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaStruk.setMargin(new Insets(10, 10, 10, 10));
        areaStruk.setText("Silakan pilih kursi dan lengkapi\ndata pemesanan di sebelah kiri.");

        // Intermediate Container: JScrollPane
        JScrollPane scrollStruk = new JScrollPane(areaStruk);
        scrollStruk.setBorder(null);
        panelStruk.add(scrollStruk, BorderLayout.CENTER);

        panelTengah.add(panelInputKiri);
        panelTengah.add(panelStruk);
        add(panelTengah, BorderLayout.CENTER);

        // C. Panel Bawah / Footer (Total & Tombol Aksi)
        JPanel panelFooter = new JPanel(new BorderLayout());
        panelFooter.setBackground(PANEL_RAISIN);
        panelFooter.setBorder(new EmptyBorder(10, 15, 10, 15));

        lblTotalHarga = new JLabel("Estimasi Total: Rp 0");
        lblTotalHarga.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTotalHarga.setForeground(TEXT_ROSE);

        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelTombol.setOpaque(false);

        btnReset = buatTombol("Reset", ACCENT_BURGUNDY);
        btnPesan = buatTombol("Cetak Tiket", BTN_WINE);

        // Registrasi Event Listener untuk tombol & radio button[cite: 1]
        btnReset.addActionListener(this);
        btnPesan.addActionListener(this);
        rbReguler.addActionListener(this);
        rbVIP.addActionListener(this);

        panelTombol.add(btnReset);
        panelTombol.add(btnPesan);

        panelFooter.add(lblTotalHarga, BorderLayout.WEST);
        panelFooter.add(panelTombol, BorderLayout.EAST);
        add(panelFooter, BorderLayout.SOUTH);
    }

    // =================================================================
    // 4. EVENT HANDLING (Implementasi ActionListener)[cite: 1]
    // =================================================================
    @Override
    public void actionPerformed(ActionEvent e) {
        Object sumber = e.getSource();

        // Update warna tombol kursi dan kalkulasi harga secara real-time
        for (JToggleButton kursi : btnKursi) {
            if (kursi.isSelected()) {
                kursi.setBackground(SEAT_SELECTED);
            } else {
                kursi.setBackground(ACCENT_BURGUNDY);
            }
        }
        hitungEstimasiHarga();

        // Jika tombol Cetak Tiket ditekan
        if (sumber == btnPesan) {
            prosesPemesanan();
        } 
        // Jika tombol Reset ditekan
        else if (sumber == btnReset) {
            resetForm();
        }
    }

    private void hitungEstimasiHarga() {
        int jumlahKursi = 0;
        for (JToggleButton kursi : btnKursi) {
            if (kursi.isSelected()) jumlahKursi++;
        }
        int hargaSatuan = rbVIP.isSelected() ? HARGA_VIP : HARGA_REGULER;
        int total = jumlahKursi * hargaSatuan;
        lblTotalHarga.setText("Estimasi Total: Rp " + String.format("%,d", total));
    }

    private void prosesPemesanan() {
        String nama = txtNama.getText().trim();
        StringBuilder kursiTerpilih = new StringBuilder();
        int jumlahKursi = 0;

        for (JToggleButton kursi : btnKursi) {
            if (kursi.isSelected()) {
                kursiTerpilih.append(kursi.getText()).append(" ");
                jumlahKursi++;
            }
        }

        if (nama.isEmpty() || jumlahKursi == 0) {
            JOptionPane.showMessageDialog(this,
                    "Harap isi nama pemesan dan pilih minimal 1 kursi!",
                    "Peringatan Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String film = (String) cbFilm.getSelectedItem();
        String tipe = rbVIP.isSelected() ? "VIP" : "Reguler";
        int total = jumlahKursi * (rbVIP.isSelected() ? HARGA_VIP : HARGA_REGULER);

        areaStruk.setText(
                "===================================\n" +
                "          TIKET CINEMAX            \n" +
                "===================================\n" +
                "Nama Pemesan : " + nama + "\n" +
                "Judul Film   : " + film + "\n" +
                "Kelas Studio : " + tipe + "\n" +
                "Nomor Kursi  : " + kursiTerpilih.toString().trim() + "\n" +
                "Jumlah Tiket : " + jumlahKursi + " Lembar\n" +
                "-----------------------------------\n" +
                "TOTAL BAYAR  : Rp " + String.format("%,d", total) + "\n" +
                "===================================\n" +
                "   Terima kasih & selamat menonton!"
        );
    }

    private void resetForm() {
        txtNama.setText("");
        cbFilm.setSelectedIndex(0);
        rbReguler.setSelected(true);
        for (JToggleButton kursi : btnKursi) {
            kursi.setSelected(false);
            kursi.setBackground(ACCENT_BURGUNDY);
        }
        lblTotalHarga.setText("Estimasi Total: Rp 0");
        areaStruk.setText("Silakan pilih kursi dan lengkapi\ndata pemesanan di sebelah kiri.");
    }

    // Method bantuan (Helper) untuk merapikan pembuatan komponen Atomic
    private JLabel buatLabel(String teks) {
        JLabel lbl = new JLabel(teks);
        lbl.setForeground(TEXT_ROSE);
        return lbl;
    }

    private void aturGayaRadio(JRadioButton rb) {
        rb.setBackground(PANEL_RAISIN);
        rb.setForeground(TEXT_ROSE);
        rb.setFocusPainted(false);
    }

    private JButton buatTombol(String teks, Color warnaBg) {
        JButton btn = new JButton(teks);
        btn.setBackground(warnaBg);
        btn.setForeground(TEXT_ROSE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(TEXT_ROSE, 1),
                new EmptyBorder(6, 14, 6, 14)
        ));
        return btn;
    }

    private TitledBorder buatBorderJudul(String judul) {
        TitledBorder border = BorderFactory.createTitledBorder(
                new LineBorder(ACCENT_BURGUNDY, 1), judul);
        border.setTitleColor(TEXT_ROSE);
        border.setTitleFont(new Font("SansSerif", Font.BOLD, 12));
        return border;
    }

    public static void main(String[] args) {
        // Menjalankan di Event Dispatch Thread (EDT)[cite: 1]
        SwingUtilities.invokeLater(() -> {
            new TiketBioskopApp().setVisible(true);
        });
    }
}