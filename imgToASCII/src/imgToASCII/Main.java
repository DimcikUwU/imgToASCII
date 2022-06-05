package imgToASCII;


	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.io.File;
	import javax.swing.JButton;
	import javax.swing.JFileChooser;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;


	public class Main extends JFrame
	{

	    private final Convert cv = new Convert();
	    private boolean hasfile = false;

	    public Main()
	    {
	        this.setSize(300, 230);
	        this.setTitle("Convert image to ASCII art");
	        this.setResizable(false);
	        components();
	        this.setLocationRelativeTo(null);
	        this.setVisible(true);
	        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    }

	    private void components()
	    {
	        JPanel jp = new JPanel();
	        jp.setLayout(null);
	        JLabel title = new JLabel("Convert image to ASCII art");
	        title.setBounds(0, 0, 300, 40);
	        title.setFont(new Font("console", 15, 15));
	        title.setHorizontalAlignment(JLabel.CENTER);
	        JLabel path1 = new JLabel();
	        path1.setBounds(0, 30, 300, 40);
	        path1.setFont(new Font("console", 12, 12));
	        path1.setHorizontalAlignment(JLabel.CENTER);
	        JFileChooser jfc = new JFileChooser();
	        JButton image = new JButton("Choose image");
	        image.setBounds(70, 70, 150, 40);
	        image.setFont(new Font("console", 12, 12));
	        image.addActionListener((ActionEvent e) ->
	        {
	            int returnVal = jfc.showOpenDialog(this);
	            if (returnVal == JFileChooser.APPROVE_OPTION)
	            {
	                File file = jfc.getSelectedFile();
	                path1.setText(file.getAbsolutePath());
	                cv.setImage(file);
	                hasfile = true;
	            }
	        });
	        JButton convert = new JButton("Convert");
	        convert.setBounds(70, 130, 150, 40);
	        convert.setFont(new Font("console", 12, 12));
	        convert.addActionListener((ActionEvent e) ->
	        {
	            if (hasfile && cv.isImage())
	            {
	                int num = cv.start();
	                File file = new File("imageASCII" + num + ".txt");
	                JOptionPane.showMessageDialog(null, "The file was successfully created in: " + file.getAbsolutePath());
	            } else
	                JOptionPane.showMessageDialog(null, "Please select a valid file");
	        });
	        jp.add(convert);
	        jp.add(title);
	        jp.add(image);
	        jp.add(path1);
	        this.getContentPane().add(jp);
	    }

	    public static void main(String[] args)
	    {
	        Main mi = new Main();
	    }
	}

