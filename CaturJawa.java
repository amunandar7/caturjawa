import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class CaturJawa extends JFrame implements ActionListener
{
	// Untuk Memberi Gambar
	ImageIcon 	ikon = new ImageIcon("file/cj.jpg"),
				icoMenu = new ImageIcon("file/menu.jpg"),
				icoExit = new ImageIcon("file/exit.jpg"),
				icoLagi = new ImageIcon("file/lagi.jpg"),
				icoLanjut = new ImageIcon("file/lanjut.jpg"),
				icoBaru = new ImageIcon("file/baru.jpg"),
				icoNama = new ImageIcon("file/nama.jpg"),
				icoWarna = new ImageIcon("file/warna.jpg"),
				icoAbout = new ImageIcon("file/about.jpg"),
				icoLihat = new ImageIcon("file/lihat.jpg"),
				icoDel = new ImageIcon("file/del.jpg");
	JMenuBar menuUtama = new JMenuBar();
	JMenuItem 	menu = new JMenuItem(icoMenu),  mnuExit = new JMenuItem(icoExit);
	// Button Dalam Menu
	JButton 	baru = new JButton(icoBaru), // New Game
				nama = new JButton(icoNama), // Set Player Name
				warna = new JButton(icoWarna), // Set Color
				lanjut = new JButton(icoLanjut), // Continue
				lihat = new JButton(icoLihat), // View Score
				del = new JButton(icoDel), // Cleare Score
				about = new JButton(icoAbout), // About
				lagi = new JButton(icoLagi); // Play Again
	JPanel 	pnlPilihan = new JPanel(),  // Panel Pilihan Menu
			pnlMenu = new JPanel(), // Panel Untuk Tombol Menu Yang Ada di Atas
			pnlKiri = new JPanel(), // Container Untuk Panel yang Ada di kiri
			pnlKiri2 = new JPanel(), // Container Untuk Panel yang ada di kiri bawah pnlMenu
			pnlInfo = new JPanel(), // panel untuk info dalam permainan
			pnlMain = new JPanel(), // Container pnlPlay
			pnlPlay = new JPanel(), // Container Utuk Tempat Main
			pnlAtas = new JPanel(), // menampilkan giliran untuk main
			pnlScore = new JPanel(), // panel untuk menampilkan score
			mainlagi = new JPanel(), // container untuk tombol "Play Again"
			tmptmain = new JPanel(); // Container untuk tombolmain[]
	// Cek menang
	int nMenang[][] = new int[12][3];
	static int n;
	static int k = 5;
	// Button Untuk Papan Permainan
	JButton tombolmain[] = new JButton[k*k+1];
	String Symbol1, Symbol2; // Untuk menentukan symbol untuk pemain
	JLabel 	logo = new JLabel(ikon),
				lblGiliran = new JLabel("", JLabel.CENTER),
				lblStatus = new JLabel("G A M E  S C O R E", JLabel.CENTER),
				lblStatus1 = new JLabel("", JLabel.LEFT), // Score Pemain Pertama
				lblStatus2 = new JLabel("", JLabel.LEFT), // Score Pemain Kedua
				label = new JLabel("============", JLabel.CENTER), // Tampilan di Panel Info
				label2 = new JLabel("============", JLabel.CENTER); // Tampilan di Panel Info
	JTextArea 	lblScore = new 	JTextArea(), // Untuk menampilkan seluruh score
				txtMessage = new JTextArea(); // Untuk menampilkan pesan
	JScrollPane scroll = new JScrollPane(); // di pasang pada pnlScore
	// setting warna
	int mainColorR = 50, mainColorG = 50, mainColorB = 250,
		btnColorR = 50, btnColorG = 50, btnColorB = 50;
		Color warnamenang = new Color(250, 250, 250);
	static int x = 1, // untuk menentukan giliran main
		jml1 = 0, // jumlah menang 1
		jml2 = 0, // jumlah menang 2
		// untuk merubah warna tombol yang menang
		tombolmenang1 = 1,
		tombolmenang2 = 1,
		tombolmenang3 = 1;
	int option; // untuk pilihan pada pesan konfirm
	boolean inGame = false;
	boolean win = false;
	boolean tmblmainClicked = false; // untuk menetukan papan permainan bisa di isi atau tidak
	public static String	message, giliran, pemenang, winner,loser,
							Player1 = "Player 1",Player2 = "Player 2",
							str1, str2, nilai1, nilai2,nilai3, nilai4,nilai5,waktuu;
	static String bln[] = {"January","February","March","April","May","June","July","August","Septenber","October","November","December"};
	static PrintWriter fw = null;
	// untuk mendapatkan selisihwaktu waktu
	public static Date mulai, selesai;
	public static long wktMulai, wktSelesai, selisihwaktu, selisihwaktu_s, selisihwaktu_m, selisihwaktu_ms;
// variable besttime diberi nilai tinggi agar nilainya tidak menjadi "0" jika datanya kosong
	public static long besttime = 999999999;
	public static int high = 0, low, Scoretinggi, Scorerendah;
	int First = 1; // untuk menentukan giliran pertama

	public CaturJawa()
	{
	//Setting Tampilan
		pnlMenu.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlPlay.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlAtas.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
		pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
		pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
		pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
		pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
		pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
		pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
		pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
		lblGiliran.setForeground(Color.white);
		lblGiliran.setFont(new Font("sansserif", Font.BOLD, 25));
		lblStatus.setForeground(Color.white);
		lblStatus1.setForeground(Color.white);
		lblStatus2.setForeground(Color.white);
		label.setForeground(Color.white);
		label2.setForeground(Color.white);
		lblStatus.setFont(new Font("sansserif", Font.ITALIC , 20));
		lblStatus1.setFont(new Font("sansserif", Font.BOLD, 18));
		lblStatus2.setFont(new Font("sansserif", Font.BOLD, 18));
		label.setFont(new Font("sansserif", Font.BOLD, 30));
		label2.setFont(new Font("sansserif", Font.BOLD, 30));
		lblScore.setFont(new Font("serif", Font.BOLD, 25));
		lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
		lblScore.setForeground(Color.white);
// setting warna tombol menu
		menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
		mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
		menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
		nama.setBackground(Color.black);
		lanjut.setBackground(Color.black);
		baru.setBackground(Color.black);
		lihat.setBackground(Color.black);
		del.setBackground(Color.black);
		warna.setBackground(Color.black);
		about.setBackground(Color.black);
		lagi.setBackground(Color.black);
// pilihan dalam menu
		pnlPilihan.setLayout(new GridLayout(7, 1));
		pnlPilihan.add(lanjut);
		pnlPilihan.add(baru);
		pnlPilihan.add(nama);
		pnlPilihan.add(warna);
		pnlPilihan.add(lihat);
		pnlPilihan.add(del);
		pnlPilihan.add(about);
// tombol menu, lagi , & lanjut tidak dapat di klik pada saat permainan baru di mulai.
		menu.setEnabled(false);
		lagi.setEnabled(false);
		lanjut.setEnabled(false);
// panel info di tampilkan ketika permainan sedang berjalan
		pnlInfo.setLayout(new GridLayout(8,1));
		pnlInfo.add(label);
		pnlInfo.add(mainlagi);
		pnlInfo.add(label2);
		pnlInfo.add(lblStatus);
		pnlInfo.add(label);
		pnlInfo.add(lblStatus1);
		pnlInfo.add(lblStatus2);
// memberikan actionlistener untuk setiap tombol
		menu.addActionListener(this);
		mnuExit.addActionListener(this);
		baru.addActionListener(this);
		baru.setActionCommand("NEWGAME");
		nama.addActionListener(this);
		lanjut.addActionListener(this);
		lagi.addActionListener(this);
		warna.addActionListener(this);
		lihat.addActionListener(this);
		del.addActionListener(this);
		about.addActionListener(this);
// memasukan panel-panel pada container
		mainlagi.setLayout(new GridLayout(1, 2, 2, 2));
		mainlagi.add(lagi);
		menuUtama.add(menu);
		menuUtama.add(mnuExit);
		pnlMenu.add(menuUtama);
		pnlMain.add(logo);
		pnlKiri2.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlKiri2.add(pnlPilihan);
		pnlKiri.setLayout(new BorderLayout());
		pnlKiri.add(pnlMenu, BorderLayout.NORTH);
		pnlKiri.add(pnlKiri2, BorderLayout.CENTER);
		this.setSize(770, 520);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.add(pnlKiri, BorderLayout.WEST);
		this.add(pnlMain, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("CATUR JAWA GAME V.2");
		this.setVisible(true);
	}
	public void setUkuran()
	{

		String [] SIZE = { "3 x 3","4 x 4","5 x 5"};
		int pilih = JOptionPane.showOptionDialog
		(
			null,
			"Choose Size of Board!",
			"Set Size of Board",
			0,
			JOptionPane.INFORMATION_MESSAGE,
			null,
			SIZE,
			SIZE[2]
		);
		if(pilih == 0)
		{
			try
			{
				for (int i=1; i<=k*k; i++)
				{
						tmptmain.remove(tombolmain[i]);
				}
			}
			catch (Exception e)
			{}
			k = 3;
			//Setting papan permainan
			tmptmain.setLayout(new GridLayout(3, 3, 5, 5));
			tmptmain.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			for(int i=1; i<=9; i++)
			{
				tombolmain[i] = new JButton();
				tombolmain[i].setBackground(new Color(btnColorR, btnColorG, btnColorB));
				tombolmain[i].addActionListener(this);
				tmptmain.add(tombolmain[i]);
			}
		}
		if(pilih == 1)
		{
			try
			{
				for (int i=1; i<=k*k; i++)
				{
						tmptmain.remove(tombolmain[i]);
				}
			}
			catch (Exception e)
			{}
			k = 4;
			//Setting papan permainan
			tmptmain.setLayout(new GridLayout(4, 4, 5, 5));
			tmptmain.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			for(int i=1; i<=16; i++)
			{
				tombolmain[i] = new JButton();
				tombolmain[i].setBackground(new Color(btnColorR, btnColorG, btnColorB));
				tombolmain[i].addActionListener(this);
				tmptmain.add(tombolmain[i]);
			}
		}
		if(pilih == 2)
		{
			try
			{
				for (int i=1; i<=k*k; i++)
				{
						tmptmain.remove(tombolmain[i]);
				}
			}
			catch (Exception e)
			{}
			k = 5;
			//Setting papan permainan
			tmptmain.setLayout(new GridLayout(5, 5, 3, 3));
			tmptmain.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			for(int i=1; i<=25; i++)
			{
				tombolmain[i] = new JButton();
				tombolmain[i].setBackground(new Color(btnColorR, btnColorG, btnColorB));
				tombolmain[i].addActionListener(this);
				tmptmain.add(tombolmain[i]);
			}
		}
	}
// tampilan ketika game di mulai
	public void showGame()
	{
		hapusPanel();
		menu.setEnabled(true);
		pnlKiri2.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlKiri2.add(pnlInfo);
		pnlMain.setLayout(new BorderLayout());
		pnlPlay.setLayout(new BorderLayout());
		pnlAtas.setLayout(new BorderLayout());
		pnlPlay.add(tmptmain);
		pnlAtas.add(lblGiliran, BorderLayout.CENTER);
		pnlMain.add(pnlPlay, BorderLayout.CENTER);
		pnlMain.add(pnlAtas, BorderLayout.NORTH);
		tmptmain.requestFocus();
		inGame = true;
		cekGiliran();
		tampilStatusMenang();
	}
// untuk setting papan permainan
	public void gameBaru()
	{
		// mengembalikan warna tombol menang ketika bermain kembali
		tombolmain[tombolmenang1].setBackground(new Color(btnColorR, btnColorG, btnColorB));
		tombolmain[tombolmenang2].setBackground(new Color(btnColorR, btnColorG, btnColorB));
		tombolmain[tombolmenang3].setBackground(new Color(btnColorR, btnColorG, btnColorB));
		for(int i=1; i<k*k+1; i++)
		{
			// semua text pada tombol di kosongkan kembali
			tombolmain[i].setText("");
			tombolmain[i].setEnabled(true);
		}
		x = 1;
		win = false;
		showGame();
		// mengambil waktu ketika mulai
		Date mulai = new Date();
		wktMulai = mulai.getTime();
		/*
		if (First==1)
			showMessage(Player1+" First Run");
		else
			showMessage(Player2+" First Run");
			*/
	}
// untuk mengacak giliran pertama
	public void random()
	{
		Date mulai = new Date();
		wktMulai = mulai.getTime();
		if ((wktMulai % 2)==1)
			First = 1;
		else
			First = 2;
	}
// untuk mengecek score
	public static void cekScore()
	{
		try
		{
			java.io.File file13 = new java.io.File("file/best_score.txt");
			Scanner input = new Scanner(file13);
			input.useDelimiter(";");
			nilai1 = input.next();
			nilai2 = input.next();
			nilai3 = input.next();
			nilai4 = input.next();
			nilai5 = input.next();
			besttime = Long.parseLong(nilai1);
			high = Integer.parseInt(nilai3);
			low = Integer.parseInt(nilai4);
			input.close();
		}
		catch(Exception e)
		{}
	}
// mencari nilai tertinggi
	public static void nilaitertinggi()
	{
		cekScore();
		if (selisihwaktu<besttime)
			besttime = selisihwaktu;

		if (high<Scoretinggi)
		{
			high = Scoretinggi;
			low = Scorerendah;
		}
	}
// menyimpan score tertinggi setelah nilai tertinggi di cek
	public static void bestScores()
	{
		nilaitertinggi();
		Calendar cal = Calendar.getInstance();
		try
		{
			FileWriter filee = new FileWriter("file/best_score.txt");
			fw = new PrintWriter(filee);
		}
		catch(IOException ioe)
		{}
		fw.print(besttime);
		fw.print(";");
		if (selisihwaktu == besttime)
		{
			fw.println("BEST TIME");
			fw.print(pemenang);
			fw.print(" => ");
			fw.println(waktuu);
			fw.print("Date	: ");
			fw.print(bln[cal.get(Calendar.MONTH)]+" ");
			fw.print(cal.get(Calendar.DATE)+" ");
			fw.println(cal.get(Calendar.YEAR));
			fw.print("At	: ");
			fw.print(cal.get(Calendar.HOUR)+":");
			fw.print(cal.get(Calendar.MINUTE));
		}
		else
		{
			fw.print(nilai2);
		}
		fw.print(";");
		fw.print(high);
		fw.print(";");
		fw.print(low);
		fw.print(";");
		if (high==Scoretinggi)
		{
			fw.println("HIGH SCORE");
			fw.println(winner+"	: "+Scoretinggi);
			fw.println(loser+"	: "+Scorerendah);
			fw.print("Date	: ");
			fw.print(bln[cal.get(Calendar.MONTH)]+" ");
			fw.print(cal.get(Calendar.DATE)+" ");
			fw.println(cal.get(Calendar.YEAR));
			fw.print("At	: ");
			fw.print(cal.get(Calendar.HOUR)+":");
			fw.print(cal.get(Calendar.MINUTE));
		}
		else
		{
			fw.print(nilai5);
		}
		fw.print(";");
		fw.close();
	}
// untuk membaca file Score, jika filenya ada
public static void baca()
	{
		try
		{
			str1 = null;
			str2 = null;
			java.io.File file1 = new java.io.File("file/GameScores.txt");
			Scanner input = new Scanner(file1);
			input.useDelimiter("`");
			str1 = input.next();
			str2 = input.next();
			input.close();
		}
		catch(Exception e)
		{}
	}
// untuk mencetak score dalam file GameScores.txt
	public static void cetakScore()
	{

		baca();
		Calendar cal = Calendar.getInstance();
		try
		{
			FileWriter file = new FileWriter("file/GameScores.txt");
			fw = new PrintWriter(file);
		}
		catch(IOException ioe)
		{}
		fw.println("**************************************");
		fw.println("           CATUR JAWA GAME SCORES");
		fw.println("**************************************");
		if (nilai2 != null)
		{
			fw.println(nilai2);
			fw.println();
		}
		if (nilai5 != null)
		{
			fw.println(nilai5);
			fw.println();
		}
		fw.println();
		fw.println("ALL SCORE:");
		fw.println("-------------------");
		fw.println();
		fw.print("`");
		if (str2 != null)
		{
			fw.println(str2);
			fw.println();
		}
		fw.print("Date	: ");
		fw.print(bln[cal.get(Calendar.MONTH)]+" ");
		fw.print(cal.get(Calendar.DATE)+" ");
		fw.println(cal.get(Calendar.YEAR));
		fw.print("At	: ");
		fw.print(cal.get(Calendar.HOUR)+":");
		fw.println(cal.get(Calendar.MINUTE));
		fw.println("Player1	: "+Player1+" => "+jml1);
		fw.println("Player2	: "+Player2+" => "+jml2);
		fw.println("Winner	: "+pemenang);
		fw.print("Time	: "+waktuu);
		fw.println("`");
		fw.close();
	}
// Untuk menghapus score
	public static void hapus()
	{
		// method hapus2 di pakai untuk mencegah nullPointerException
		hapus2();
		try
		{
			FileWriter f = new FileWriter("file/GameScores.txt");
			PrintWriter fw = new PrintWriter(f);
		}
		catch(IOException ioe)
		{}
		fw.print("");
		fw.close();
	}
	public static void hapus2()
	{
		try
		{
			FileWriter f2 = new FileWriter("file/best_score.txt");
			fw = new PrintWriter(f2);
		}
		catch(IOException ioe)
		{}
		fw.print("");
		fw.close();
	}
// menghitung selisih waktu mulai dengan waktu selesai
	public void cekWaktu()
	{
			Date selesai = new Date();
			wktSelesai = selesai.getTime();
			selisihwaktu = wktSelesai - wktMulai;
			selisihwaktu_s = (selisihwaktu/1000)%60;
			selisihwaktu_m = (selisihwaktu/1000)/60;
			selisihwaktu_ms = selisihwaktu%1000;
			waktuu = selisihwaktu_m+":"+selisihwaktu_s+":"+selisihwaktu_ms;
			if (jml1>jml2)
			{
				Scoretinggi=jml1;
				Scorerendah=jml2;
				winner = Player1;
				loser = Player2;
			}
			else
			{
				Scoretinggi=jml2;
				Scorerendah=jml1;
				winner = Player2;
				loser = Player1;
			}
	}
// mengecek menang
	public void cekMenang()
	{
		cekWaktu();
		for(int c=0; c<12; c++)
		{
			if	(
					(nMenang[c][0] > 0 && nMenang[c][0] < k*k+1) &&
					(nMenang[c][1] > 0 && nMenang[c][1] < k*k+1) &&
					(nMenang[c][2] > 0 && nMenang[c][2] < k*k+1)
				)
			{

					if		(
							!tombolmain[nMenang[c][0]].getText().equals("") &&
							tombolmain[nMenang[c][0]].getText().equals(tombolmain[nMenang[c][1]].getText()) &&
							tombolmain[nMenang[c][1]].getText().equals(tombolmain[nMenang[c][2]].getText())
							) // 	maksudnya adalah jika tombol yang pertama sama dengan tombol kedua
							//		dan tombol kedua sama dengan tombol ketiga.
							//		di cek berdasarkan array pada nMenang
						{
							win = true;
							// untuk merubah warna tombol yang menang
							tombolmenang1 = nMenang[c][0];
							tombolmenang2 = nMenang[c][1];
							tombolmenang3 = nMenang[c][2];
							tombolmain[tombolmenang1].setBackground(warnamenang);
							tombolmain[tombolmenang2].setBackground(warnamenang);
							tombolmain[tombolmenang3].setBackground(warnamenang);
							break;
						}
				System.out.println(c);
				System.out.print(nMenang[c][0]+" , ");
				System.out.print(nMenang[c][1]+" + ");
				System.out.println(nMenang[c][2]);
				System.out.println("-------------------------");
				System.out.println();
			}
		}
		if(win || (!win && x>k*k))
		{
			if(win)
			{
				if((x % 2 == 0 && First == 1)||(x % 2 != 0 && First == 2))
				{
						message = Player1 + " has won";
						pemenang = Player1;
						jml1++;
						First = 2;
				}
				else
				{
						message = Player2 + " has won";
						pemenang = Player2;
						jml2++;
						First = 1;
				}
				win = false;
				cekWaktu();
				bestScores(); // method ini dipanggil 2x untuk menghindari
				bestScores(); // nilai null jika filenya belum dibuat.
			}
			else if(!win && x>k*k)
			{
				message = "Draw !!!";
				pemenang = "-";
				if (First == 1)
					First = 2;
				else
					First = 1;
			}
			showMessage(message);
			// tombol main tidak bisa di klik ketika permainan selesai
			for(int i=1; i<=k*k; i++)
			{
				tombolmain[i].setEnabled(false);
			}
			lagi.setEnabled(true);
			tampilStatusMenang();
			cetakScore();
		}
		else
			cekGiliran();
	}
// method untuk melihat score
	public void lihatScore()
	{
		hapusPanel();
		baca();
		scroll.remove(lblScore);
		scroll = new JScrollPane(lblScore);
		try
		{
			if (str1.equals("nullnull"))
			{
				lblScore.setText("SCORE IS EMPTY");
			}
			else
			{
				lblScore.setText(str1 + str2);
			}
		}
		catch (NullPointerException np)
		{
			lblScore.setText("SCORE IS EMPTY");
		}
		lblScore.setEditable(false);
		pnlMain.setLayout(new GridLayout(1,1));
		pnlScore.setLayout(new GridLayout(1,1));
		pnlScore.add(scroll);
		pnlMain.add(pnlScore);
		pnlKiri2.add(pnlPilihan);
	}
	public void cekGiliran()
	{
		if (First == 1)
		{
			if(!(x % 2 == 0))
			{
				giliran = Player1 + " [ "+ Symbol1 + " ]";
			}
			else
			{
				giliran = Player2 + " [ "+ Symbol2 + " ]";
			}
		}
		if (First == 2)
		{
			if(!(x % 2 == 0))
			{
				giliran = Player2 + " [ "+ Symbol2 + " ]";
			}
			else
			{
				giliran = Player1 + " [ "+ Symbol1 + " ]";
			}
		}
		lblGiliran.setText("Turn: "+giliran);
	}
// Menampilkan jumlah menang dari setiap pemain ketika game sedang berjalan
	public void tampilStatusMenang()
	{
		lblStatus1.setText(" [ " + Symbol1 + " ] " +Player1 + " : \n"+ jml1);
		lblStatus2.setText(" [ " + Symbol2 + " ] " +Player2 + " : "+ jml2);
	}
// method untuk setting nama
	public void setNama()
	{
		String namabaru = Player2;

		namabaru = getInput("Enter Player 1 name :", Player1);
		if(namabaru == null)
		{ setNama(); }
		else if(namabaru.equals(""))
		{
			showMessage("Invalid Name!");
			setNama();
		}
		else if(namabaru.length()>20)
		{
			showMessage("The Name too Long!");
			setNama();
		}
		else if(namabaru.equals(Player2))
		{
			option = askMessage("Player 1 name matches Player 2's\nDo you want to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION)
				Player1 = namabaru;
			else
				setNama();
		}
		else if(namabaru != null)
		{
			Player1 = namabaru;
			setNama2();
		}
	}
	public void setNama2()
	{
		String namabaru;
		namabaru = getInput("Enter Player 2 name:", Player2);
		if(namabaru == null)
		{ setNama2(); }
		else if(namabaru.equals(""))
		{
			showMessage("Invalid Name!");
			setNama2();
		}
		else if(namabaru.length()>20)
		{
			showMessage("The Name too Long!");
			setNama2();
		}
		else if(namabaru.equals(Player1))
		{
			option = askMessage("Player 2 name matches Player 1's\nDo you want to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION)
				Player2 = namabaru;
			else
				setNama2();
		}
		else if(namabaru != null)
		{
			Player2 = namabaru;
			pilihSymbol();
		}
	}
	public void pilihSymbol()
	{
		String Symbol[] = { " X "," O "};
		int pilih = JOptionPane.showOptionDialog
		(
			null,
			"Choose "+Player1+" Symbol!",
			"Set Player 1 Symbol",
			0,
			JOptionPane.INFORMATION_MESSAGE,
			null,
			Symbol,
			Symbol[1]
		);
		if (pilih == 0)
		{
			Symbol1= "X";
			Symbol2= "O";
		}
		if (pilih == 1)
		{
			Symbol1= "O";
			Symbol2= "X";
		}
		showMessage(Player1+" Symbol is : "+Symbol1);
		showMessage(Player2+" Symbol is : "+Symbol2);
	}
// method untuk mengubah warna
	public void setwarna()
	{
		String [] wrn = { "Red","Green","Blue","white","Purple","Aqua","Yellow","Pink","Brown","Orange","Silver","Black"};
		int pilih = JOptionPane.showOptionDialog
		(
			null,
			"Choose The Color!",
			"Set Color",
			0,
			JOptionPane.INFORMATION_MESSAGE,
			null,
			wrn,
			wrn[11]
		);
		if(pilih == 0) // warna merah
		{
			mainColorR = 250; mainColorG = 50; mainColorB = 50;
			pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
			pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			tmptmain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			lblScore.setForeground(Color.white);
			lblGiliran.setForeground(Color.white);
			lblStatus.setForeground(Color.white);
			lblStatus1.setForeground(Color.white);
			lblStatus2.setForeground(Color.white);
			label.setForeground(Color.white);
			label2.setForeground(Color.white);
		}
		else if(pilih == 1) // warna hijau
		{
			mainColorR = 50; mainColorG = 250; mainColorB = 50;
			pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
			pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			tmptmain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			lblScore.setForeground(Color.black);
			lblGiliran.setForeground(Color.black);
			lblStatus.setForeground(Color.black);
			lblStatus1.setForeground(Color.black);
			lblStatus2.setForeground(Color.black);
			label.setForeground(Color.black);
			label2.setForeground(Color.black);
		}
		else if(pilih == 2) // warna biru
		{
			mainColorR = 50; mainColorG = 50; mainColorB = 250;
			pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
			pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			tmptmain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			lblScore.setForeground(Color.white);
			lblGiliran.setForeground(Color.white);
			lblStatus.setForeground(Color.white);
			lblStatus1.setForeground(Color.white);
			lblStatus2.setForeground(Color.white);
			label.setForeground(Color.white);
			label2.setForeground(Color.white);
		}
		else if(pilih == 3) // warna putih
		{
			mainColorR = 250; mainColorG = 250; mainColorB = 250;
			pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
			pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			tmptmain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			lblScore.setForeground(Color.black);
			lblGiliran.setForeground(Color.black);
			lblStatus.setForeground(Color.black);
			lblStatus1.setForeground(Color.black);
			lblStatus2.setForeground(Color.black);
			label.setForeground(Color.black);
			label2.setForeground(Color.black);
		}
		else if(pilih == 4) // warna ungu
		{
			mainColorR = 150; mainColorG = 50; mainColorB = 250;
			pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
			pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			tmptmain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			lblScore.setForeground(Color.white);
			lblGiliran.setForeground(Color.white);
			lblStatus.setForeground(Color.white);
			lblStatus1.setForeground(Color.white);
			lblStatus2.setForeground(Color.white);
			label.setForeground(Color.white);
			label2.setForeground(Color.white);
		}
		else if(pilih == 5) // warna aqua
		{
			mainColorR = 45; mainColorG = 223; mainColorB = 219;
			pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
			pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			tmptmain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			lblScore.setForeground(Color.black);
			lblGiliran.setForeground(Color.black);
			lblStatus.setForeground(Color.black);
			lblStatus1.setForeground(Color.black);
			lblStatus2.setForeground(Color.black);
			label.setForeground(Color.black);
			label2.setForeground(Color.black);
		}
		else if(pilih == 6) // warna kuning
		{
			mainColorR = 255; mainColorG = 255; mainColorB = 0;
			pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
			pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			tmptmain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			lblScore.setForeground(Color.black);
			lblGiliran.setForeground(Color.black);
			lblStatus.setForeground(Color.black);
			lblStatus1.setForeground(Color.black);
			lblStatus2.setForeground(Color.black);
			label.setForeground(Color.black);
			label2.setForeground(Color.black);
		}
		else if(pilih == 7) // warna pink
		{
			mainColorR = 255; mainColorG = 128; mainColorB = 192;
			pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
			pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			tmptmain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			lblScore.setForeground(Color.black);
			lblGiliran.setForeground(Color.black);
			lblStatus.setForeground(Color.black);
			lblStatus1.setForeground(Color.black);
			lblStatus2.setForeground(Color.black);
			label.setForeground(Color.black);
			label2.setForeground(Color.black);
		}
		else if(pilih == 8) // warna coklat
		{
			mainColorR = 128; mainColorG = 64; mainColorB = 0;
			pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
			pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			tmptmain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			lblScore.setForeground(Color.white);
			lblGiliran.setForeground(Color.white);
			lblStatus.setForeground(Color.white);
			lblStatus1.setForeground(Color.white);
			lblStatus2.setForeground(Color.white);
			label.setForeground(Color.white);
			label2.setForeground(Color.white);
		}
		else if(pilih == 9) // warna orange
		{
			mainColorR = 255; mainColorG = 128; mainColorB = 0;
			pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
			pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			tmptmain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			lblScore.setForeground(Color.black);
			lblGiliran.setForeground(Color.black);
			lblStatus.setForeground(Color.black);
			lblStatus1.setForeground(Color.black);
			lblStatus2.setForeground(Color.black);
			label.setForeground(Color.black);
			label2.setForeground(Color.black);
		}
		else if(pilih == 10) // warna silver
		{
			mainColorR = 192; mainColorG = 192; mainColorB = 192;
			pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
			pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			tmptmain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			lblScore.setForeground(Color.black);
			lblGiliran.setForeground(Color.black);
			lblStatus.setForeground(Color.black);
			lblStatus1.setForeground(Color.black);
			lblStatus2.setForeground(Color.black);
			label.setForeground(Color.black);
			label2.setForeground(Color.black);
		}
		else if(pilih == 11) // warna hitam
		{
			mainColorR = 50; mainColorG = 50; mainColorB = 50;
			pnlPilihan.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			pnlMenu.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlMain.setBackground(new Color((mainColorR), mainColorG, mainColorB));
			pnlPlay.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlAtas.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			tmptmain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
			pnlKiri.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlKiri2.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			pnlInfo.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			menu.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			mnuExit.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			menuUtama.setBackground(new Color(mainColorR , mainColorG , mainColorB));
			lblScore.setBackground(new Color((mainColorR ), (mainColorG ), (mainColorB)));
			lblScore.setForeground(Color.white);
			lblGiliran.setForeground(Color.white);
			lblStatus.setForeground(Color.white);
			lblStatus1.setForeground(Color.white);
			lblStatus2.setForeground(Color.white);
			label.setForeground(Color.white);
			label2.setForeground(Color.white);
		}
	}
// Untuk Menampilkan Message
	public int askMessage(String message, String title, int option)
	{
		return JOptionPane.showConfirmDialog(null, message, title, option);
	}
	public String getInput(String message, String setText)
	{
		return JOptionPane.showInputDialog(null, message, setText);
	}
	public void showMessage(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}
// untuk mengganti panel
	public void hapusPanel()
	{
		pnlKiri2.remove(pnlPilihan);
		pnlKiri2.remove(pnlInfo);
		pnlMain.remove(logo);
		pnlMain.remove(pnlPlay);
		pnlMain.remove(pnlAtas);
		pnlScore.remove(scroll);
		pnlMain.remove(pnlScore);
		pnlPlay.remove(pnlPilihan);
		pnlPlay.remove(txtMessage);
		pnlPlay.remove(tmptmain);
		pnlAtas.remove(lblGiliran);
		pnlAtas.remove(mainlagi);
	}
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		for(int i=1; i<=k*k; i++)
		{
			if(source == tombolmain[i] && x <	k*k+1)
			{
				n = i;
				//*******************
				// horizontal posisi n di kiri
				nMenang[0][0] = n;
				nMenang[0][1] = n+1;
				nMenang[0][2] = ((((n+1+k)/k)-((n-1+k)/k))*(k*k*k))+n+2;
				//*******************
				// horizontal posisi n di tengah
				nMenang[1][0] = n-1;
				nMenang[1][1] = n;
				nMenang[1][2] = ((((n-2+k)/k)-((n+k)/k))*(k*k*k))+n+1;
				//*******************
				// horizontal posisi n di kanan
				nMenang[2][0] = n-2;
				nMenang[2][1] = n-1;
				nMenang[2][2] = ((((n-3)/k)-((n-1)/k))*(k*k*k))+n;
				//*******************
				// vertikal posisi n di atas
				nMenang[3][0] = n;
				nMenang[3][1] = n+k;
				nMenang[3][2] = n+k+k;
				//*******************
				// vertikal posisi n di tengah
				nMenang[4][0] = n-k;
				nMenang[4][1] = n;
				nMenang[4][2] = n+k;
				//*******************
				// vertikal posisi n di bawah
				nMenang[5][0] = n-k-k;
				nMenang[5][1] = n-k;
				nMenang[5][2] = n;
				//*******************
				// diagonal bawah posisi n di kiri
				nMenang[6][0] = n;
				nMenang[6][1] = n+1+k;
				nMenang[6][2] = ((((n+1+k)/k)-((n-1+k)/k))*(k*k*k))+n+2+k+k;
				//*******************
				// diagonal bawah posisi n di tengah
				nMenang[7][0] = n-1-k;
				nMenang[7][1] = n;
				nMenang[7][2] = ((((n-2+k)/k)-((n+k)/k))*(k*k*k))+n+1+k;
				//*******************
				// diagonal bawah posisi n di kanan
				nMenang[8][0] = n-2-k-k;
				nMenang[8][1] = n-1-k;
				nMenang[8][2] = ((((n-3)/k)-((n-1)/k))*(k*k*k))+n;
				//*******************
				// diagonal atas posisi n di kiri
				nMenang[9][0] = n;
				nMenang[9][1] = n-(k-1);
				nMenang[9][2] = ((((n+1+k+k)/k)-((n+k+k)/k))*(k*k*k))+n-((k*2)-2);
				//*******************
				// diagonal atas posisi n di tengah
				nMenang[10][0] = n+(k-1);
				nMenang[10][1] = n;
				nMenang[10][2] = ((((n-2+k)/k)-((n+k)/k))*(k*k*k))+n-(k-1);
				//*******************
				// diagonal atas posisi n di kanan
				nMenang[11][0] = n+((k*2)-2);
				nMenang[11][1] = n+(k-1);
				nMenang[11][2] = ((((n-2+k)/k)-((n-1+k)/k))*(k*k*k))+n;
				if ( k == 3)
					tombolmain[i].setFont(new Font("sansserif", Font.BOLD, 150));
				if ( k == 4)
					tombolmain[i].setFont(new Font("sansserif", Font.BOLD, 100));
				if ( k == 5)
					tombolmain[i].setFont(new Font("sansserif", Font.BOLD, 80));
				tmblmainClicked = true;
				if (First == 1)
				{
					if(!(x % 2 == 0))
					{
						tombolmain[i].setText(Symbol1);
					}
					else
					{
						tombolmain[i].setText(Symbol2);
					}
				}
				if (First == 2)
				{
					if(!(x % 2 == 0))
					{
						tombolmain[i].setText(Symbol2);
					}
					else
					{
						tombolmain[i].setText(Symbol1);
					}
				}
				tombolmain[i].setEnabled(false);
				tmptmain.requestFocus();
				x++;
			}
		}
		if(tmblmainClicked)
		{
			cekMenang();
			tmblmainClicked = false;
		}
		if(source == menu)
		{
			hapusPanel();
			menu.setEnabled(false);

			if(source == menu)
			{
				pnlKiri2.setLayout(new FlowLayout(FlowLayout.CENTER));
				pnlKiri2.add(pnlPilihan);
				pnlMain.setLayout(new FlowLayout(FlowLayout.CENTER));
				pnlMain.add(logo);

			}
			pnlMain.add(pnlPlay);
		}
		else if(e.getActionCommand().equals("NEWGAME"))
		{
			if(inGame)
			{
				option = askMessage("Are you sure you want to start new game?"+ "\n if you start a new game, last score will be lost",
					"New Game?" ,JOptionPane.YES_NO_OPTION
				);
				if(option == JOptionPane.YES_OPTION)
					inGame = false;
					jml1 = 0;
					jml2 = 0;
			}
			if(!inGame)
			{
				if(source == baru)
				{
					menu.setEnabled(true);
					lanjut.setEnabled(true);
					setUkuran();
					setNama();
					random();
					gameBaru();
							if (First==1)
								showMessage(Player1+" First Run");
							else
			showMessage(Player2+" First Run");
				}
			}
		}
		else if(source == lanjut)
		{
			menu.setEnabled(true);
			cekGiliran();
			showGame();
		}
		else if(source == nama)
		{
			setNama();
		}
		else if(source == warna)
		{
			setwarna();
		}
		else if(source == del)
		{
			option = askMessage("Are you sure you want to clear score?", "Clear Score", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION)
			{
			hapusPanel();
			pnlMain.add(logo);
			pnlKiri2.add(pnlPilihan);
			hapus();
			besttime = 999999999;
			}
		}
		else if(source == mnuExit)
		{
			option = askMessage("Are you sure you want to exit?", "Exit Game", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
		else if(source == lagi)
		{
			gameBaru();
			lagi.setEnabled(false);
		}
		else if(source == lihat)
		{
			lihatScore();
		}
		else if(source == about)
		{
			showMessage("C A T U R  J A W A  G A M E  V.2\n\nDEVELOPED BY:\nACHMAD MUNANDAR - ( CCIT FTUI '09 )");
		}
		pnlKiri.setVisible(false);
		pnlKiri.setVisible(true);
		pnlMain.setVisible(false);
		pnlMain.setVisible(true);
	}
	public static void main(String[] args)
	{
		CaturJawa cj = new CaturJawa();
	}
}