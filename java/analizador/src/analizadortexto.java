import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class AnalizadorTexto extends JFrame {
    private JTextArea areaTexto;
    private JTextArea resultadosArea;
    private File archivoActual;

    public AnalizadorTexto() {
        setTitle("Analizador de Texto");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        areaTexto = new JTextArea();
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        JScrollPane scrollTexto = new JScrollPane(areaTexto);
        add(scrollTexto, BorderLayout.CENTER);

        JMenuBar barra = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem abrir = new JMenuItem("Abrir");
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem guardarComo = new JMenuItem("Guardar como");

        abrir.addActionListener(e -> abrirArchivo());
        guardar.addActionListener(e -> guardarArchivo());
        guardarComo.addActionListener(e -> guardarArchivoComo());

        menuArchivo.add(abrir);
        menuArchivo.add(guardar);
        menuArchivo.add(guardarComo);

        JMenu menuEditar = new JMenu("Editar");
        JMenuItem copiar = new JMenuItem("Copiar");
        JMenuItem cortar = new JMenuItem("Cortar");
        JMenuItem pegar = new JMenuItem("Pegar");
        JMenuItem buscar = new JMenuItem("Buscar");
        JMenuItem reemplazar = new JMenuItem("Reemplazar");

        copiar.addActionListener(e -> areaTexto.copy());
        cortar.addActionListener(e -> areaTexto.cut());
        pegar.addActionListener(e -> areaTexto.paste());

        buscar.addActionListener(e -> buscarTexto());
        reemplazar.addActionListener(e -> reemplazarTexto());

        menuEditar.add(copiar);
        menuEditar.add(cortar);
        menuEditar.add(pegar);
        menuEditar.add(buscar);
        menuEditar.add(reemplazar);

        barra.add(menuArchivo);
        barra.add(menuEditar);
        setJMenuBar(barra);

        JPanel panelSur = new JPanel();
        panelSur.setLayout(new BorderLayout());

        JPanel filaBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 6));
        JButton procesarBtn = new JButton("Procesar");
        procesarBtn.setPreferredSize(new Dimension(110, 26));
        procesarBtn.addActionListener(e -> procesarTexto());
        filaBoton.add(procesarBtn);

        panelSur.add(filaBoton, BorderLayout.NORTH);

        resultadosArea = new JTextArea();
        resultadosArea.setEditable(false);
        resultadosArea.setRows(6);
        resultadosArea.setLineWrap(true);
        resultadosArea.setWrapStyleWord(true);
        resultadosArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollResultados = new JScrollPane(resultadosArea);
        scrollResultados.setPreferredSize(new Dimension(800, 140));

        panelSur.add(scrollResultados, BorderLayout.CENTER);

        add(panelSur, BorderLayout.SOUTH);
    }

    private void abrirArchivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de texto (.txt)", "txt"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            archivoActual = chooser.getSelectedFile();
            try {
                String contenido = new String(Files.readAllBytes(Paths.get(archivoActual.getAbsolutePath())));
                areaTexto.setText(contenido);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo.");
            }
        }
    }

    private void guardarArchivo() {
        if (archivoActual != null) {
            try (FileWriter fw = new FileWriter(archivoActual)) {
                fw.write(areaTexto.getText());
                JOptionPane.showMessageDialog(this, "Archivo guardado.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo.");
            }
        } else {
            guardarArchivoComo();
        }
    }

    private void guardarArchivoComo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de texto (.txt)", "txt"));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            archivoActual = chooser.getSelectedFile();
            if (!archivoActual.getName().toLowerCase().endsWith(".txt")) {
                archivoActual = new File(archivoActual.getAbsolutePath() + ".txt");
            }
            guardarArchivo();
        }
    }

    private void buscarTexto() {
        String palabra = JOptionPane.showInputDialog(this, "Ingrese la palabra a buscar:");
        if (palabra != null && !palabra.isEmpty()) {
            String texto = areaTexto.getText();
            int index = texto.indexOf(palabra);
            if (index >= 0) {
                areaTexto.requestFocus();
                areaTexto.setSelectionStart(index);
                areaTexto.setSelectionEnd(index + palabra.length());
            } else {
                JOptionPane.showMessageDialog(this, "Palabra no encontrada.");
            }
        }
    }

    private void reemplazarTexto() {
        String buscar = JOptionPane.showInputDialog(this, "Palabra a reemplazar:");
        if (buscar == null) return;
        String reemplazo = JOptionPane.showInputDialog(this, "Nuevo texto:");
        if (reemplazo == null) return;
        areaTexto.setText(areaTexto.getText().replace(buscar, reemplazo));
    }

    private void procesarTexto() {
        String texto = areaTexto.getText();
        if (texto == null) texto = "";
        String stats = generarEstadisticas(texto);

        // Mostrar en área inferior
        resultadosArea.setText(stats);

        // Mostrar también en ventana emergente
        JOptionPane.showMessageDialog(this, stats, "Resultados del Análisis", JOptionPane.INFORMATION_MESSAGE);
    }

    private String generarEstadisticas(String texto) {
        StringBuilder sb = new StringBuilder();
        String t = texto;
        String tTrim = t.trim();

        int totalCaracteres = t.length();
        int totalCaracteresSinEspacios = t.replaceAll("\\s+", "").length();
        int totalPalabras = tTrim.isEmpty() ? 0 : tTrim.split("\\s+").length;
        int totalLineas = t.isEmpty() ? 0 : t.split("\\r?\\n").length;
        int totalOraciones = contarOraciones(t);

        int vocales = 0;
        int consonantes = 0;
        for (char c : t.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                if ("aeiouáéíóúü".indexOf(c) >= 0) vocales++;
                else consonantes++;
            }
        }

        double avgLongPalabra = 0;
        int sumaLong = 0;
        List<String> palabrasLista = new ArrayList<>();
        if (totalPalabras > 0) {
            String[] palabras = tTrim.split("\\s+");
            for (String w : palabras) {
                String limpio = w.replaceAll("[^\\p{L}\\p{N}]", "");
                if (!limpio.isEmpty()) {
                    palabrasLista.add(limpio.toLowerCase());
                    sumaLong += limpio.length();
                }
            }
            if (!palabrasLista.isEmpty()) {
                avgLongPalabra = (double) sumaLong / palabrasLista.size();
            }
        }

        Map<String, Integer> freq = new HashMap<>();
        for (String w : palabrasLista) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }
        List<Map.Entry<String, Integer>> freqSorted = freq.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toList());

        String palabraMasLarga = "";
        for (String w : palabrasLista) {
            if (w.length() > palabraMasLarga.length()) palabraMasLarga = w;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        sb.append("Longitud del texto (caracteres): ").append(totalCaracteres).append("\n");
        sb.append("Longitud sin espacios: ").append(totalCaracteresSinEspacios).append("\n");
        sb.append("Total de palabras: ").append(totalPalabras).append("\n");
        sb.append("Total de líneas: ").append(totalLineas).append("\n");
        sb.append("Total de oraciones: ").append(totalOraciones).append("\n");
        sb.append("Vocales: ").append(vocales).append("   Consonantes: ").append(consonantes).append("\n");
        sb.append("Palabra más larga: ").append(palabraMasLarga.isEmpty() ? "-" : palabraMasLarga).append("\n");
        sb.append("Promedio longitud palabra: ").append(palabrasLista.isEmpty() ? "0" : df.format(avgLongPalabra)).append("\n");

        sb.append("Palabras más frecuentes: ");
        if (freqSorted.isEmpty()) {
            sb.append("-\n");
        } else {
            int limit = Math.min(5, freqSorted.size());
            for (int i = 0; i < limit; i++) {
                Map.Entry<String, Integer> e = freqSorted.get(i);
                sb.append(e.getKey()).append(" (").append(e.getValue()).append(")");
                if (i < limit - 1) sb.append(", ");
            }
            sb.append("\n");
        }

        int aCount = contarCaracteres(texto, "aáAÁ");
        int eCount = contarCaracteres(texto, "eéEÉ");
        int iCount = contarCaracteres(texto, "iíIÍ");
        int oCount = contarCaracteres(texto, "oóOÓ");
        int uCount = contarCaracteres(texto, "uúüUÚÜ");

        sb.append("Repeticiones de 'A', 'a' o 'á': ").append(aCount).append("\n");
        sb.append("Repeticiones de 'E', 'e' o 'é': ").append(eCount).append("\n");
        sb.append("Repeticiones de 'I', 'i' o 'í': ").append(iCount).append("\n");
        sb.append("Repeticiones de 'O', 'o' o 'ó': ").append(oCount).append("\n");
        sb.append("Repeticiones de 'U', 'u' o 'ú': ").append(uCount).append("\n");

        int par = 0, impar = 0;
        for (String p : palabrasLista) {
            if (p.length() % 2 == 0) par++;
            else impar++;
        }
        sb.append("Palabras con cantidad de caracteres par: ").append(par).append("\n");
        sb.append("Palabras con cantidad de caracteres impar: ").append(impar).append("\n");

        sb.append("\n--- Traducción a clave Murciélago ---\n");
        sb.append(traducirMurcielago(t)).append("\n");

        return sb.toString();
    }

    private int contarCaracteres(String texto, String caracteres) {
        int count = 0;
        for (char c : texto.toCharArray()) {
            if (caracteres.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    private int contarOraciones(String t) {
        if (t == null || t.trim().isEmpty()) return 0;
        String[] parts = t.split("[\\.\\?!]+");
        int count = 0;
        for (String s : parts) {
            if (!s.trim().isEmpty()) count++;
        }
        return count;
    }

    private String traducirMurcielago(String texto) {
        String clave = "murcielago";
        String numeros = "0123456789";
        StringBuilder resultado = new StringBuilder();
        if (texto == null) return "";
        for (char ch : texto.toLowerCase().toCharArray()) {
            int idx = clave.indexOf(ch);
            if (idx != -1) resultado.append(numeros.charAt(idx));
            else resultado.append(ch);
        }
        return resultado.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AnalizadorTexto().setVisible(true));
    }
}
