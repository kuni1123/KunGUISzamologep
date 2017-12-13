import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ablak {
    private JPanel panel1;
    int minuszDarab = 0;
    private JButton bt_szamol;
    private JTextField tf_szamok;
    private JComboBox cb_elvalasztoKarakterek;
    public String osszeg="";

    String minuszok = "Nem írhatóak be negatív számok. Az ön által megadott negatív számok: ";
    String add(String[] szamok){
        int osszeg2=0;
        try {
            for (int i = 0; i < szamok.length; i++) {
                if (Integer.parseInt(szamok[i]) < 0) {
                    minuszok += " " + szamok[i];
                    minuszDarab++;
                } else
                    osszeg2 += Integer.parseInt(szamok[i]);
            }
            osszeg = Integer.toString(osszeg2);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Hiba történt: "+ex.getMessage());
            osszeg="Hiba";
        }
        return osszeg;
    }

    public Ablak() {
        bt_szamol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String osszeg="";
                Character[] karakterek = {',','!','%','/','=',':','_','>',';','<'};
                String karakter = ""+karakterek[cb_elvalasztoKarakterek.getSelectedIndex()];
                try{
                    String[] szamok = tf_szamok.getText().split(karakter);
                    osszeg = add(szamok);
                    if(minuszDarab>0){
                        JOptionPane.showMessageDialog(null,minuszok+"");
                    }else {
                        if(osszeg!="Hiba")
                            JOptionPane.showMessageDialog(null, "A beírt számok összege: " + osszeg);
                    }
                }catch(Exception ex){
                    if(osszeg!="Hiba")
                    JOptionPane.showMessageDialog(null,"Nem a megfelelő elválasztó karaktert használta!");

                }
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Számológép");
        frame.setContentPane(new Ablak().getPanel1());
        Dimension meret = new Dimension(300, 200);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        frame.setLocation(((int) width / 2) - meret.width / 2, ((int) height / 2) - meret.height / 2);
        frame.setMinimumSize(meret);
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.pack();
        frame.setVisible(true);
    }
}


