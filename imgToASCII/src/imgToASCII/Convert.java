package imgToASCII;


	import java.awt.Color;
	import java.awt.Graphics2D;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import javax.imageio.ImageIO;


	public class Convert
	{

	    // Declaring the variables that will be used in the program.
	    private BufferedImage img;
	    private final char[] asciiCode =
	    {
	        '!', '"', '#', '$', '%', '&', '(', ')', '*', '+', ' '
	    };

	    public void setImage(File file)
	    {
	        try
	        {
	            img = ImageIO.read(file);
	        } catch (IOException ex)
	        {
	            System.err.println(ex.getMessage());
	        }
	    }


	    public boolean isImage()
	    {
	        try
	        {
	            img.getRGB(0, 0);
	            return true;
	        } catch (Exception ex)
	        {
	            return false;
	        }
	    }

	    /**
	     * It takes an image, resizes it, converts it to greyscale, and then converts it to ASCII
	     *
	     * @return The number of characters in the ASCII art.
	     */
	    public int start()
	    {
	        img = resize();
	        toGrey();
	        return toAscii();
	    }

	    /**
	     * It takes an image, and returns a new image that is 200 pixels wide, and has the same aspect ratio as the original
	     * image
	     *
	     * @return A BufferedImage object
	     */
	    private BufferedImage resize()
	    {
	        int sizeX = 200;
	        int sizeY = (200 * img.getHeight()) / img.getWidth();
	        BufferedImage dimg = new BufferedImage(sizeX, sizeY, img.getType());
	        Graphics2D g2d = dimg.createGraphics();
	        g2d.drawImage(img, 0, 0, sizeX, sizeY, null);
	        g2d.dispose();
	        return dimg;
	    }

	    /**
	     * It takes the average of the red, green, and blue values of each pixel and sets the red, green, and blue values of
	     * each pixel to that average
	     */
	    private void toGrey()
	    {
	        int mediaPixel, colorsRGB;
	        Color colorAux;
	        for (int i = 0; i < img.getWidth(); i++)
	            for (int j = 0; j < img.getHeight(); j++)
	            {
	                colorAux = new Color(img.getRGB(i, j));
	                mediaPixel = (int) ((colorAux.getRed() + colorAux.getGreen() + colorAux.getBlue()) / 3);
	                colorsRGB = (mediaPixel << 16) | (mediaPixel << 8) | mediaPixel;
	                img.setRGB(i, j, colorsRGB);
	            }
	    }

	    // Creating a new file and writing the ASCII art to the file.
	    private int toAscii()
	    {
	        String asciitxt = "";
	        Color color;
	        FileWriter fw = null;
	        File filecont;
	        int cont = 0;
	        while (true)
	        {
	            filecont = new File("imageASCII" + cont + ".txt");
	            if (!filecont.isFile())
	            {
	                try
	                {
	                    filecont.createNewFile();
	                } catch (IOException ex)
	                {
	                    System.out.println(ex.getMessage());
	                }
	                break;
	            }
	            cont++;
	        }
	        try
	        // Creating a new file writer and writing the ASCII art to the file.
	        {
	            fw = new FileWriter("imageASCII" + cont + ".txt");
	        } catch (IOException ex)
	        {
	            System.err.println(ex.getMessage());
	        }
	        for (int i = 0; i < img.getHeight(); i++)
	        {
	            for (int j = 0; j < img.getWidth(); j++)
	            {
	                color = new Color(img.getRGB(j, i));
	                int sum = 0;
	                for (int k = 0; k <= 10; k++)
	                {
	                    if (color.getRed() >= sum && color.getRed() <= sum + 25.5)
	                    {
	                        asciitxt += asciiCode[k];
	                        break;
	                    }
	                    sum += 25.5;
	                }
	            }
	            asciitxt += "\n";
	        }
	        try
	        {
	            fw.write(asciitxt);
	            fw.close();
	        } catch (IOException ex)
	        {
	            System.err.println(ex.getMessage());
	        }
	        return cont;
	    }
	}


