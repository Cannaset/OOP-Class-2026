# CinemaX – Aplikasi Pemesanan Tiket Bioskop (Java Swing)

Aplikasi desktop sederhana untuk memesan tiket bioskop, dibuat dengan **Java Swing**. Pengguna dapat mengisi nama pemesan, memilih film, memilih kelas studio (Reguler/VIP), memilih kursi, lalu mencetak ringkasan tiket. Tampilan memakai tema warna **hitam–maroon**.

---

## Informasi Kode

| Item | Keterangan |
|------|------------|
| **Nama file** | `TiketBioskopApp.java` |
| **Kelas utama** | `TiketBioskopApp` (`extends JFrame implements ActionListener`) |
| **Bahasa / Library** | Java, `javax.swing` dan `java.awt` |
| **Ukuran jendela** | 750 × 520 piksel, tampil di tengah layar |
| **Harga tiket** | Reguler Rp 45.000, VIP Rp 75.000 per kursi |
| **Daftar film** | Dune: Part Two, Oppenheimer, Interstellar, The Batman |
| **Kursi tersedia** | 8 kursi (A1–A4 dan B1–B4) |

### Fitur Utama
- Input nama pemesan dan pilihan film.
- Pilihan kelas studio (Reguler / VIP) dengan radio button.
- Pemilihan beberapa kursi sekaligus; kursi terpilih berubah warna menjadi crimson.
- **Estimasi total harga real-time** pada footer setiap kali kursi atau kelas studio diubah.
- Validasi input: nama tidak boleh kosong dan minimal 1 kursi harus dipilih.
- Tombol **Cetak Tiket** untuk menampilkan struk, dan tombol **Reset** untuk mengosongkan form.

### Palet Warna
| Konstanta | Hex | Fungsi |
|-----------|-----|--------|
| `BG_ONYX` | `#161214` | Latar belakang utama |
| `PANEL_RAISIN` | `#26191D` | Latar panel |
| `ACCENT_BURGUNDY` | `#451822` | Header dan aksen |
| `BTN_WINE` | `#7A1F34` | Tombol utama |
| `SEAT_SELECTED` | `#A8304B` | Kursi terpilih |
| `TEXT_ROSE` | `#E6CED4` | Warna teks |

### Cara Menjalankan
```bash
javac TiketBioskopApp.java
java TiketBioskopApp
```
Membutuhkan JDK 8 atau lebih baru.

---

## 5 Bagian/Komponen GUI pada Kode

Kode ini menjadi contoh dari 5 bagian GUI Java Swing berikut.

### 1. Top-Level Container
Wadah tingkat paling atas yang menjadi jendela aplikasi. Pada kode ini digunakan **`JFrame`** dengan mewarisi (`extends`) kelas tersebut.

```java
public class TiketBioskopApp extends JFrame implements ActionListener {
    ...
    setTitle("CinemaX - Pemesanan Tiket Bioskop");
    setSize(750, 520);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
```

| Komponen | Fungsi |
|----------|--------|
| `JFrame` | Jendela utama aplikasi (judul, ukuran, tombol close, posisi tengah layar) |

---

### 2. Intermediate Containers
Wadah perantara yang dipakai untuk mengelompokkan dan menata komponen lain di dalam `JFrame`.

| Komponen | Variabel | Fungsi |
|----------|----------|--------|
| `JPanel` | `panelHeader` | Bagian judul di atas |
| `JPanel` | `panelTengah` | Membagi area tengah menjadi dua kolom (form kiri, struk kanan) |
| `JPanel` | `panelInputKiri` | Menampung form data dan pemilihan kursi |
| `JPanel` | `panelForm` | Form data pemesanan (nama, film, kelas studio) |
| `JPanel` | `panelKursi` | Grid tombol kursi |
| `JPanel` | `panelStruk` | Wadah ringkasan tiket |
| `JPanel` | `panelFooter`, `panelTombol` | Total harga dan tombol aksi |
| `JScrollPane` | `scrollStruk` | Membungkus `JTextArea` agar dapat di-scroll |

```java
JScrollPane scrollStruk = new JScrollPane(areaStruk);
panelStruk.add(scrollStruk, BorderLayout.CENTER);
```

---

### 3. Atomic Components
Komponen dasar yang berinteraksi langsung dengan pengguna dan tidak berfungsi sebagai wadah.

| Komponen | Variabel | Fungsi |
|----------|----------|--------|
| `JLabel` | `lblJudul`, `lblNama`, `lblFilm`, `lblTipe`, `lblTotalHarga` | Menampilkan teks/label |
| `JTextField` | `txtNama` | Input nama pemesan |
| `JComboBox<String>` | `cbFilm` | Dropdown pilihan film |
| `JRadioButton` + `ButtonGroup` | `rbReguler`, `rbVIP`, `bgTipeKursi` | Memilih satu kelas studio |
| `JToggleButton[]` | `btnKursi` | Tombol on/off untuk memilih kursi (A1–B4) |
| `JTextArea` | `areaStruk` | Menampilkan struk/ringkasan tiket (read-only) |
| `JButton` | `btnPesan`, `btnReset` | Tombol Cetak Tiket dan Reset |

```java
txtNama = new JTextField();
cbFilm = new JComboBox<>(daftarFilm);
rbReguler = new JRadioButton("Reguler (45rb)", true);
btnKursi[i] = new JToggleButton(kodeKursi[i]);
```

---

### 4. Layout Manager
Pengatur posisi dan ukuran komponen di dalam container.

| Layout | Digunakan pada | Tujuan |
|--------|----------------|--------|
| `BorderLayout` | `JFrame` utama (NORTH/CENTER/SOUTH), `panelInputKiri`, `panelStruk`, `panelFooter` | Membagi area atas, tengah, bawah, kiri, dan kanan |
| `GridLayout` | `panelTengah` (1×2), `panelForm` (4×2), `panelKursi` (2×4) | Menyusun komponen dalam grid yang rapi |
| `FlowLayout` | `panelTombol` (rata kanan) | Menyusun tombol berurutan secara horizontal |

```java
setLayout(new BorderLayout(12, 12));
JPanel panelTengah = new JPanel(new GridLayout(1, 2, 12, 0));
JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
```

---

### 5. Event Handling
Mekanisme menanggapi aksi pengguna. Kelas mengimplementasikan **`ActionListener`** dan mendaftarkan dirinya sebagai listener lewat `addActionListener(this)`.

| Sumber event | Reaksi |
|--------------|--------|
| `btnKursi[i]` (klik kursi) | Warna kursi berubah dan estimasi total diperbarui |
| `rbReguler` / `rbVIP` | Estimasi total dihitung ulang sesuai kelas studio |
| `btnPesan` | Memvalidasi input lalu menampilkan struk (`prosesPemesanan()`) |
| `btnReset` | Mengembalikan form ke kondisi awal (`resetForm()`) |

```java
@Override
public void actionPerformed(ActionEvent e) {
    Object sumber = e.getSource();
    ...
    if (sumber == btnPesan) {
        prosesPemesanan();
    } else if (sumber == btnReset) {
        resetForm();
    }
}
```

Validasi menggunakan dialog `JOptionPane.showMessageDialog(...)`, dan GUI dijalankan pada **Event Dispatch Thread (EDT)** melalui `SwingUtilities.invokeLater(...)` di method `main`.

---

## Ringkasan Struktur Hierarki GUI

```
JFrame (Top-Level Container)
├── panelHeader (JPanel)
│   └── JLabel
├── panelTengah (JPanel)
│   ├── panelInputKiri (JPanel)
│   │   ├── panelForm (JPanel)
│   │   │   ├── JLabel, JTextField
│   │   │   ├── JLabel, JComboBox
│   │   │   └── JLabel, JRadioButton ×2
│   │   └── panelKursi (JPanel)
│   │       └── JToggleButton ×8
│   └── panelStruk (JPanel)
│       └── JScrollPane
│           └── JTextArea
└── panelFooter (JPanel)
    ├── JLabel (total harga)
    └── panelTombol (JPanel)
        └── JButton ×2 (Reset, Cetak Tiket)
```

---

## Konsep Pemrograman Lain yang Digunakan
- **Enkapsulasi**: atribut komponen dideklarasikan `private`.
- **Method helper**: `buatLabel()`, `aturGayaRadio()`, `buatTombol()`, `buatBorderJudul()` untuk mengurangi kode berulang.
- **Border kustom**: `EmptyBorder`, `LineBorder`, dan `TitledBorder`.
- **Format angka**: `String.format("%,d", total)` untuk pemisah ribuan.

## Screenshoot Aplikasi
![GUI 1](./Picture/imagegui.png)
![GUI 2](./Picture/imagegui2.png)
![GUI 3](./Picture/imagegui3.png)