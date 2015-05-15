package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.Valuta;

public class GUIKontroler {
	private static MenjacnicaGUI menjacnica;
	private static MenjacnicaInterface menjacnicaint;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menjacnicaint = new Menjacnica();
					menjacnica = new MenjacnicaGUI();
					menjacnica.setVisible(true);
					menjacnica.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(menjacnica.getContentPane(),
				"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak",
				JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	public static void prikaziDodajKursGUI() {
		DodajKursGUI prozor = new DodajKursGUI(menjacnica);
		prozor.setLocationRelativeTo(menjacnica.getContentPane());
		prozor.setVisible(true);
	}
	public static void prikaziObrisiKursGUI(JList list) {
		if (list.getSelectedValue() != null) {
			ObrisiKursGUI prozor = new ObrisiKursGUI(menjacnica,
					(Valuta) (list.getSelectedValue()));
			prozor.setLocationRelativeTo(menjacnica.getContentPane());
			prozor.setVisible(true);
		}
	}
	public static void prikaziIzvrsiZamenuGUI(JList list) {
		if (list.getSelectedValue() != null) {
			IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(menjacnica,
					(Valuta) (list.getSelectedValue()));
			prozor.setLocationRelativeTo(menjacnica.getContentPane());
			prozor.setVisible(true);
		}
	}
	public static void ucitajIzFajla(Menjacnica sistem,JList list) {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(menjacnica.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				sistem.ucitajIzFajla(file.getAbsolutePath());
				prikaziSveValute(list, sistem);
			}	
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(menjacnica.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void prikaziSveValute(JList list, Menjacnica sistem) {
		list.setListData(sistem.vratiKursnuListu().toArray());

	}
	public static void sacuvajUFajl(Menjacnica sistem) {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(menjacnica.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				sistem.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(menjacnica.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void prikaziAboutProzor(){
		JOptionPane.showMessageDialog(menjacnica.getContentPane(),
				"Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",
				JOptionPane.INFORMATION_MESSAGE);
	}
	public static void unesiKurs(String naziv,String skraceniNaziv,int sifra,String prodajniKurs,String kupovniKurs,String srednjiKurs) {
		try {
			Valuta valuta = new Valuta();

			// Punjenje podataka o valuti
			valuta.setNaziv(naziv);
			valuta.setSkraceniNaziv(skraceniNaziv);
			valuta.setSifra((Integer)(sifra));
			valuta.setProdajni(Double.parseDouble(prodajniKurs);
			valuta.setKupovni(Double.parseDouble(kupovniKurs);
			valuta.setSrednji(Double.parseDouble(srednjiKurs);
			
			// Dodavanje valute u kursnu listu
			menjacnica.sistem.dodajValutu(valuta);

			// Osvezavanje glavnog prozora
			menjacnica.prikaziSveValute(list,sistem);
			
			//Zatvaranje DodajValutuGUI prozora
			dispose();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(menjacnica.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
}
