import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fondskalkualtor {
        public static void main(String[] args) throws Exception {

            JFrame vindu = new JFrame("Sparekalkulator for fond");

            //Labels
            JLabel aar_L = new JLabel("Antall år med sparing: ");
            JLabel startKapital_L = new JLabel("Startkapital");
            JLabel sparingPerMnd_L = new JLabel("Antall kr sparing pr mnd.: ");
            JLabel forvaltningsKostnad_L = new JLabel("Forvaltningskostnad %: ");
            JLabel forventetAvkastning_L = new JLabel("Forventet avkastning %: ");
            
            //Textfields
            JTextField aar_TF = new JTextField();
            JTextField startKapital_TF = new JTextField();
            JTextField sparingPerMnd_TF = new JTextField();
            JTextField forvaltningsKostnad_TF = new JTextField();
            JTextField forventetAvkastning_TF = new JTextField();
            
            // Buttons
            JButton buttonOk = new JButton("Beregn");

            // Textarea
            JTextArea output = new JTextArea();
            

            //placement of objects in frame
            
            aar_L.setBounds(20,20,150,20);
            aar_TF.setBounds(170,20,50,20);

            startKapital_L.setBounds(300,20,150,20);
            startKapital_TF.setBounds(400,20,50,20);
            
            sparingPerMnd_L.setBounds(20,60,150,20);
            sparingPerMnd_TF.setBounds(170,60,50,20);

            forvaltningsKostnad_L.setBounds(20,100,150,20);
            forvaltningsKostnad_TF.setBounds(170,100,50,20);
            
            forventetAvkastning_L.setBounds(20,140,150,20);
            forventetAvkastning_TF.setBounds(170,140,50,20);

            output.setBounds(20,190,300,200);
            buttonOk.setBounds(20,400,100,40);
                
            buttonOk.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                output.setText(null);
                    try {
                        int aar = Integer.parseInt(aar_TF.getText());
                        double startKapital = Double.parseDouble(startKapital_TF.getText());
                        double sparingPerMnd = Double.parseDouble(sparingPerMnd_TF.getText());
                        double forvaltningsKostnad = Double.parseDouble(forvaltningsKostnad_TF.getText());
                        double forventetAvkastning = Double.parseDouble(forventetAvkastning_TF.getText());
                        output.append(totalAvkastning(aar, startKapital, sparingPerMnd, forvaltningsKostnad, forventetAvkastning));
                }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(vindu, "Feil i innverdi \n" + ex, "Feil", JOptionPane.ERROR_MESSAGE);
                    }
            }
        });
            // add objects to frame
            vindu.add(aar_L);
            vindu.add(aar_TF);
            vindu.add(startKapital_L);
            vindu.add(startKapital_TF);            
            vindu.add(sparingPerMnd_L);
            vindu.add(sparingPerMnd_TF);
            vindu.add(forvaltningsKostnad_L);
            vindu.add(forvaltningsKostnad_TF);
            vindu.add(forventetAvkastning_L);
            vindu.add(forventetAvkastning_TF);
            vindu.add(output);
            vindu.add(buttonOk);
            vindu.setSize(600,500);
            vindu.setLayout(null);
            vindu.setVisible(true);
            vindu.setResizable(false);
            vindu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static String totalAvkastning (int aar, double startKapital, double sparingPerMnd, double forvaltning, double forventetAvkastning) {
        
        int months = aar * 12;
        double totaltSpart = 0;
        double innskutt = 0;
        double summertEtterSkatt = 0;
 
        totaltSpart += startKapital;
 
        for (int i = 0; i < months; i++) {
            totaltSpart += sparingPerMnd;
            totaltSpart += (totaltSpart * ((forventetAvkastning/12)/100));
            totaltSpart -= (totaltSpart * (forvaltning/12/100));
            innskutt += sparingPerMnd;
        }       
 
        double totaltInnskutt = innskutt + startKapital;
        double avkastningMinusInnskutt = totaltSpart - totaltInnskutt;
        summertEtterSkatt = avkastningMinusInnskutt * 0.75;    
        
        String oppsummering = "Totalt oppspart: " + String.valueOf((int)totaltSpart) + "kr \n" + 
        "Totalt innskutt: " + String.valueOf((int)totaltInnskutt) + "kr \n" + 
        "Avkastning minus innskutt: " + String.valueOf((int)avkastningMinusInnskutt) + "kr \n" + 
        "Sum etter skatt (25%): " + String.valueOf((int)summertEtterSkatt) + "kr";
        return oppsummering;
    }

}
