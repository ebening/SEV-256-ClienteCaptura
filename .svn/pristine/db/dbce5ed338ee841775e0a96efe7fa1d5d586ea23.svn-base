package com.adinfi.sevCaptura.resources;


/*
@copyright(disclaimer) 
Licensed Materials - Property of IBM
IBM DB2 Content Manager Enterprise Edition V8 (program number 5724-B19)
(c ) Copyright IBM Corp. 1998, 2005.  All Rights Reserved.

US Government Users Restricted Rights                                  
Use, duplication or disclosure restricted by GSA ADP Schedule          
Contract with IBM Corp.     

DISCLAIMER OF WARRANTIES :                                             
                                                                       
Permission is granted to copy and modify this  Sample code, and to           
distribute modified versions provided that both the copyright        
notice, and this permission notice and warranty disclaimer appear
in all copies and modified versions. 

THIS SAMPLE CODE IS LICENSED TO YOU AS-IS. IBM AND ITS SUPPLIERS AND
LICENSORS DISCLAIM ALL WARRANTIES, EITHER EXPRESS OR IMPLIED, IN SUCH SAMPLE
CODE, INCLUDING THE WARRANTY OF NON-INFRINGEMENT AND THE IMPLIED WARRANTIES
OF MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  IN NO EVENT WILL
IBM OR ITS LICENSORS OR SUPPLIERS BE LIABLE FOR ANY DAMAGES ARISING OUT OF
THE USE OF OR INABILITY TO USE THE SAMPLE CODE, DISTRIBUTION OF THE SAMPLE
CODE, OR COMBINATION OF THE SAMPLE CODE WITH ANY OTHER CODE. IN NO EVENT
SHALL IBM OR ITS LICENSORS AND SUPPLIERS BE LIABLE FOR ANY LOST REVENUE,
LOST PROFITS OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
OF LIABILITY, EVEN IF IBM OR ITS LICENSORS OR SUPPLIERS HAVE BEEN ADVISED OF
THE POSSIBILITY OF SUCH DAMAGES.                                     
                                                                 
@endCopyright
*/

import com.ibm.mm.viewer.*;
import com.ibm.mm.viewer.annotation.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import java.net.URL;

/**
 * This sample demonstrates building a stand alone viewer application with annotations support
 * using the GenericDocViewer framework.   Files are read and written by this sample rather
 * than reading and writing content to a server.
 * <p>
 * Conventions are used to associate all files related to a single document:
 * <ul>
 * <li>&lt.filename&gt..&lt.extension&gt. -- as entered by the user is the main (base) part of the document.
 * <li>&lt.filename&gt..t_l -- is used for the associated annotations.
 * <li>&lt.filename&gt.&lt.n&gt..&lt.extension&gt. -- is used for additional document parts, where &lt.n&gt. is
 *       the index of the additional part (starting with 1).
 * </ul>
 *
 * Please read the <CMBROOT>/Samples/java/viewer/readme.html for instructions on how to setup and run
 * the samples.
 */
public class TGenericDocViewer	implements	CMBGenericDocSaveListener,CMBGenericDocClosedListener,CMBGenericDocHelpListener {

	/* --- Instance Variables --- */

	private boolean traceEnabled = false;
	private CMBGenericDocViewer genericDocViewer;
	private CMBStreamingDocServices docServices;
	private CMBAnnotationServices annoServices;
	private Hashtable docToFilename = new Hashtable();
	// associate CMBDocument instances to filenames
	private StatusArea statusArea;
	private JPanel panelVisor;
	

	/* --- Static Fields --- */
	private static final String VIEWER_DEFAULT_CONFIG_FILE = "ConfiguracionViewer.properties";
	private static boolean SAVE_VIEWER_CONFIG = true;
	// change to true to save/restore viewer configuration
	
	// A character encoding that indicates the default for the current locale should be used
	private static final String DEFAULT_CHARACTER_ENCODING = "Default";
	
	// Supported character encodings for plain text documents (human readable)
	private static final String[] CHARACTER_ENCODINGS = new String[] {
			DEFAULT_CHARACTER_ENCODING,
			"Arabic (ISO-8859-6)",
			"Arabic (Windows-1256)",
			"Baltic (ISO-8859-4)",
			"Baltic (ISO-8859-13)",
			"Baltic (Windows-1257)",
			"Chinese Simplified (GB18030)",
			"Chinese Simplified (Windows-936)",
			"Chinese Simplified (EUC-CN)",
			"Chinese Traditional (Big5)",
			"Chinese Traditional (Windows-950)",
			"Chinese Traditional (EUC-TW)",
			"Central European (Windows-1250)",
			"Central European (ISO-8859-2)",
			"Cyrillic (Windows-1251)",
			"Cyrillic (ISO-8859-5)",
			"Cyrillic (KOI8-R)",
			"Greek (ISO-8859-7)",
			"Greek (Windows-1253)",
			"Hebrew (ISO-8859-8)",
			"Hebrew (Windows-1255)",
			"Japanese (EUC-JP)",
			"Japanese (Shift-JIS)",
			"Korean (Windows-949)",
			"Korean (EUC-KR)",
			"South European (ISO-8859-3)",
			"Thai (Windows-874)",
			"Turkish (ISO-8859-9)",
			"Turkish (Windows-1254)",
			"Unicode (UTF-16)",
			"Unicode (UTF-8)",
			"US-ASCII",
			"Vietnamese (Windows-1258)",
			"Western (ISO-8859-1)",
			"Western (ISO-8859-15)",
			"Western (Windows-1252)"
	};
	
	// The charset names for the supported character encodings (matches the array above)
	private static String[] CHARSET_NAMES = new String[] {
			null,
			"ISO-8859-6",
			"windows-1256",
			"ISO-8859-4",
			"ISO-8859-13",
			"windows-1257",
			"GB18030",
			"windows-936",
			"EUC-CN",
			"Big5",
			"windows-950",
			"EUC-TW",
			"windows-1250",
			"ISO-8859-2",
			"windows-1251",
			"ISO-8859-5",
			"KOI8-R",
			"ISO-8859-7",
			"windows-1253",
			"ISO-8859-8",
			"windows-1255",
			"EUC-JP",
			"Shift_JIS",
			"windows-949",
			"EUC-KR",
			"ISO-8859-3",
			"windows-874",
			"ISO-8859-9",
			"windows-1254",
			"UTF-16",
			"UTF-8",
			"US-ASCII",
			"windows-1258",
			"ISO-8859-1",
			"ISO-8859-15",
			"windows-1252"
	};
	
	/* --- Static Methods --- */

	/**
	 * Main method for the sample.  An argument may be provided with the filename of
	 * a document to initially display.
	 */
	/*public static void main(final String[] args) {

		// Create the viewer frame
		/*final TGenericDocViewer gdvFrame = new TGenericDocViewer();

		// Size and position the frame appropriately (note on screens
		// smaller than 1024x768 part of the toolbar may be cut off)
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int viewerWidth = screenWidth * 7 / 8;
		int viewerHeight = screenHeight * 7 / 8;
		gdvFrame.setLocation((screenWidth - viewerWidth) / 2, 0);
		gdvFrame.setSize(viewerWidth, viewerHeight);
		gdvFrame.applyComponentOrientation(ComponentOrientation.getOrientation(Locale.getDefault()));

		// Display the frame
		gdvFrame.show();
		//gdvFrame.openDocument("/Users/mike/Documents/AdInfinitum/Clientes/7ELEVEN/Finanzas/BaseDatosv1.jpg",true);
		
		gdvFrame.openDocument("C:\\CM_CONTENT\\2012-38\\100_4570.JPG", true);
	
		// If documents were specified in the args, display them
		/*SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (int i = 0; i < args.length; i++) {
					if (i == 0) {
						gdvFrame.openDocument("/Users/mike/Documents/AdInfinitum/Clientes/7ELEVEN/Finanzas/BaseDatosv1.jpg",true);
					} else {
						gdvFrame.openDocument("/Users/mike/Documents/AdInfinitum/Clientes/7ELEVEN/Finanzas/BaseDatosv1.jpg",true);
					}
				}
			}
		});*/

	//}*/

	/**
	 * Returns the directoy containing (or should contain) the specified file.
	 */
	private static String getDirectory(String filename) {
		File file = new File(filename);
		try {
			return file.getParentFile().getCanonicalPath();
		} catch (IOException e) {
			return "";
		}
	}

	/**
	 * Returns the name portion of the file name (excluding the path and the extension).
	 */
	private static String getName(String filename) {
		String partName;
		int extIndex = filename.lastIndexOf('.');
		int separatorIndex = filename.lastIndexOf(File.separator) + 1;
		if (extIndex != -1) {
			partName = filename.substring(separatorIndex, extIndex);
		} else {
			partName = filename;
		}
		return partName;
	}

	/**
	 * Returns the name portion of the file name (including the path
	 * but excluding the extension).
	 */
	private static String getFileNameWithPath(String filename) {
		String partName;
		int extIndex = filename.lastIndexOf('.');
		if (extIndex != -1) {
			partName = filename.substring(0, extIndex);
		} else {
			partName = filename;
		}
		return partName;
	}

	/**
	 * Returns the suffix portion of the file name.
	 */
	private static String getExtension(String filename) {
		String extension;
		int extIndex = filename.lastIndexOf('.');
		if (extIndex != -1) {
			extension = filename.substring(extIndex + 1, filename.length());
		} else {
			extension = "";
		}
		return extension;
	}

	/**
	  * Returns MIME content type for typical file extensions.   This is to avoid having
	  * to prompt the user to enter the content type.   (If the document were coming
	  * from a server this information would be available from the server.)
	  */
	private static String getMimeType(String extension) {
		extension = extension.toLowerCase();
		if (extension.equals("gif")) {
			return "image/gif";
		} else if (extension.equals("jpg") || extension.equals("jpeg")) {
			return "image/jpeg";
		} else if (extension.equals("tif") || extension.equals("tiff")) {
			return "image/tiff";
		} else if (extension.equals("pdf")) {
			return "application/pdf";
		} else if (extension.equals("mda")) {
			return "application/vnd.ibm.modcap";
		} else if (extension.equals("afp")) {
			return "application/afp";
		} else if (extension.equals("txt")) {
			return "text/plain";
		} else if (extension.equals("htm") || extension.equals("html")) {
			return "text/html";
		} else if (extension.equals("rtf")) {
			return "text/richtext";
		} else if (extension.equals("doc")) {
			return "application/msword";
		} else if (extension.equals("ppt")) {
			return "application/vnd.ms-powerpoint";
		} else if (extension.equals("lwp")) {
			return "application/vnd.lotus-wordpro";
		} else if (extension.equals("123")) {
			return "application/vnd.lotus-1-2-3";
		} else if (extension.equals("prz")) {
			return "application/vnd.lotus-freelance";
		} else if (extension.equals("xls")) {
			return "application/vnd.ms-excel";
		} else if (extension.equals("wp")) {
			return "application/wordperfect5.1";
		} else if (extension.equals("bmp")) {
			return "image/bmp";
		} else if (extension.equals("pcx")) {
			return "image/pcx";
		} else if (extension.equals("dcx")) {
			return "image/dcx";
		} else if (extension.equals("xml")) {
			return "text/xml";
		} else {
			return "application/binary";
		}
	}

	public CMBGenericDocViewer getVisor(){
		return genericDocViewer;
	}
	
	public StatusArea getStatusArea(){
		return statusArea;
	}
	
	public JPanel getPanelVisor(){
		return panelVisor;
	}
	

	/* --- Constructor --- */

	/**
	 * Constructs an instance of the TGenericDocViewer frame.
	 */
	public TGenericDocViewer() {
		//super("IBM DB2 Content Manager - Java Document Viewer");
		//this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Note: for right-to-left languages, accelerators don't appear correctly on tooltips, so hide them
		/*if (ComponentOrientation
			.getOrientation(Locale.getDefault())
			.equals(ComponentOrientation.RIGHT_TO_LEFT)) {
			UIManager.put("ToolTip.hideAccelerator", new Boolean(true));
		}*/
	
		// Use system look and feel
		/*try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}*/
	
		//Have all fonts set to the menu font for accessbility
		/*try {
			Font defaultFont = UIManager.getFont("Menu.font");
			FontUIResource newFontResource =
				new FontUIResource(
					defaultFont.getFontName(),
					defaultFont.getStyle(),
					defaultFont.getSize());
			Enumeration keys = UIManager.getDefaults().keys();

			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();
				Object value = UIManager.get(key);
				if (value instanceof FontUIResource) {
					UIManager.put(key, newFontResource);
				}
			}
		} catch (Exception e) {
			System.out.println("Could not set system default font.");
		}*/

		// Create document services.
		docServices =new CMBStreamingDocServices(new StreamingDocServicesCallbacks(),null);
		// Note: Since the second argument is null, default engine properties
		// are used.  The engine properties define the rendering engines
		// that will be used and specify engine-specific parameters, including
		// page size.  See the API docs for CMBStreamingDocServices for detail
		// on the engines and their properties.  To modify engine properties,
		// you would need to create a Properties object and pass the engine
		// properties on this call.
		
		// Create annotation services
		annoServices = new CMBAnnotationServices(new AnnotationServicesCallbacks());
		annoServices.hideAllAnnotations();
		//Load the default configuration file.  
		Properties properties = null;
		try {
			//URL url =this.getClass().getClassLoader().getResource(VIEWER_DEFAULT_CONFIG_FILE);
	        URL url =ClassLoader.getSystemResource("com/adinfi/sevCaptura/resources/"+VIEWER_DEFAULT_CONFIG_FILE);    
			System.out.println("URL DE Properties: "+url.getPath());
			InputStream configFile = null;
			Properties defaultProperties = null;
			if (url != null) {
				configFile = url.openStream();
				defaultProperties = new Properties();
				defaultProperties.load(configFile);
				properties = defaultProperties;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		// Create the generic doc viewer and add it to the window
		genericDocViewer =	new CMBGenericDocViewer(docServices, annoServices, properties);
		//genericDocViewer.setPreferredSize(new Dimension(493, 706));
		genericDocViewer.setPreferredSize(new Dimension(790, 599));
		//panelVisor.setBounds(2, 37, 790, 599)
		
		panelVisor = new JPanel();
		panelVisor.add(genericDocViewer);
		//this.getContentPane().add(genericDocViewer);
		
		// Setup the menu bar
		//setupMenus();

		// Add listeners to the window and generic doc viewer
		/*this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				if (genericDocViewer.hasDocumentsPrinting()) {
					int option =
						JOptionPane.showConfirmDialog(
							null,
							"There are documents currently printing, are you sure you want to close?",
							"Confirm",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE,
							null);
					switch (option) {
						case JOptionPane.YES_OPTION :
							break;
						case JOptionPane.NO_OPTION :
							return;
						default :
							return;
					}
				}

				// Terminate the viewer
				if (!genericDocViewer.terminate()) {
					return;
				}
				System.exit(0);
			}
		});*/
		genericDocViewer.addDocSaveListener(this);
		genericDocViewer.addDocClosedListener(this);
		genericDocViewer.addDocHelpListener(this);

		// Add status area to bottom of window
		statusArea = new StatusArea();
		genericDocViewer.addDocStateChangedListener(statusArea);
		genericDocViewer.addDocSelectedListener(statusArea);
		genericDocViewer.addDocClosedListener(statusArea);
		
		//panelVisor.add(statusArea, BorderLayout.SOUTH);
		//this.getContentPane().add(statusArea, BorderLayout.SOUTH);

	}

	/* --- Methods --- */

	private JMenuBar mainMenuBar = null;
	
	public JMenuBar getBarraMenu(){
		return mainMenuBar;
	}
	
	/**
	 * Setup the menu bar for the application.
	 */
	private void setupMenus() {
		mainMenuBar = new JMenuBar();
		//setJMenuBar(mainMenuBar);
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('f');
		mainMenuBar.add(fileMenu);
		fileMenu.add(genericDocViewer.getAction("new_doc")).setMnemonic('n');
		fileMenu.add(genericDocViewer.getAction("open_doc")).setMnemonic('o');
		fileMenu.addSeparator();
		fileMenu.add(genericDocViewer.getAction("save_doc")).setMnemonic('s');
		fileMenu.add(genericDocViewer.getAction("save_as")).setMnemonic('a');
		fileMenu.addSeparator();
		fileMenu.add(genericDocViewer.getAction("export_doc")).setMnemonic('e');
		fileMenu.add(genericDocViewer.getAction("print")).setMnemonic('p');
		fileMenu.add(genericDocViewer.getAction("print_all"));
		fileMenu.addSeparator();
		fileMenu.add(genericDocViewer.getAction("close_doc")).setMnemonic('c');
		fileMenu.add(genericDocViewer.getAction("close_all_doc")).setMnemonic('l');
		
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic('e');
		mainMenuBar.add(editMenu);
		editMenu.add(genericDocViewer.getAction("undo")).setMnemonic('u');
		editMenu.add(genericDocViewer.getAction("redo")).setMnemonic('r');
		editMenu.addSeparator();
		editMenu.add(genericDocViewer.getAction("cut")).setMnemonic('t');
		editMenu.add(genericDocViewer.getAction("copy")).setMnemonic('c');
		editMenu.add(genericDocViewer.getAction("paste")).setMnemonic('p');
		editMenu.add(genericDocViewer.getAction("delete")).setMnemonic('d');
		editMenu.addSeparator();
		editMenu.add(genericDocViewer.getAction("selectAll")).setMnemonic('s');
		editMenu.add(genericDocViewer.getAction("deselectAll")).setMnemonic('e');
		editMenu.add(genericDocViewer.getAction("selectArea")).setMnemonic('a');
		editMenu.addSeparator();
		editMenu.add(genericDocViewer.getAction("preferences")).setMnemonic('f');
		
		JMenu viewMenu = new JMenu("View");
		viewMenu.setMnemonic('v');
		mainMenuBar.add(viewMenu);
		viewMenu.add(genericDocViewer.getAction("page_first")).setMnemonic('f');
		viewMenu.add(genericDocViewer.getAction("page_prev")).setMnemonic('p');
		viewMenu.add(genericDocViewer.getAction("page_next")).setMnemonic('n');
		viewMenu.add(genericDocViewer.getAction("page_last")).setMnemonic('l');
		viewMenu.add(genericDocViewer.getAction("goto_page")).setMnemonic('g');
		viewMenu.addSeparator();
		viewMenu.add(genericDocViewer.getAction("doc_first")).setMnemonic('i');
		viewMenu.add(genericDocViewer.getAction("doc_prev")).setMnemonic('r');
		viewMenu.add(genericDocViewer.getAction("doc_next")).setMnemonic('e');
		viewMenu.add(genericDocViewer.getAction("doc_last")).setMnemonic('s');
		viewMenu.addSeparator();
		viewMenu.add(genericDocViewer.getAction("showhidethumb")).setMnemonic('b');
		viewMenu.add(genericDocViewer.getAction("hide_show")).setMnemonic('a');

		JMenu imageMenu = new JMenu("Image");
		imageMenu.setMnemonic('i');
		mainMenuBar.add(imageMenu);
		imageMenu.add(genericDocViewer.getAction("enhance")).setMnemonic('e');
		imageMenu.add(genericDocViewer.getAction("invert")).setMnemonic('v');
		imageMenu.addSeparator();
		imageMenu.add(genericDocViewer.getAction("zoom_in")).setMnemonic('i');
		imageMenu.add(genericDocViewer.getAction("zoom_out")).setMnemonic('o');
		imageMenu.add(genericDocViewer.getAction("zoom_custom")).setMnemonic('z');
		imageMenu.addSeparator();
		imageMenu.add(genericDocViewer.getAction("fit_height")).setMnemonic('h');
		imageMenu.add(genericDocViewer.getAction("fit_width")).setMnemonic('w');
		imageMenu.add(genericDocViewer.getAction("fit_window")).setMnemonic('o');
		imageMenu.add(genericDocViewer.getAction("fit_actualsize")).setMnemonic('a');
		imageMenu.addSeparator();
		imageMenu.add(genericDocViewer.getAction("rotate_90")).setMnemonic('r');
		imageMenu.add(genericDocViewer.getAction("rotate_180")).setMnemonic('8');
		imageMenu.add(genericDocViewer.getAction("rotate_270")).setMnemonic('l');
		imageMenu.add(genericDocViewer.getAction("rotate_pages")).setMnemonic('p');

		JMenu annotationMenu = new JMenu("Annotation");
		annotationMenu.setMnemonic('a');
		mainMenuBar.add(annotationMenu);
		annotationMenu.add(genericDocViewer.getAction("Arrow")).setMnemonic('a');
		annotationMenu.add(genericDocViewer.getAction("Circle")).setMnemonic('c');
		annotationMenu.add(genericDocViewer.getAction("Highlight")).setMnemonic('h');
		annotationMenu.add(genericDocViewer.getAction("Line")).setMnemonic('l');
		annotationMenu.add(genericDocViewer.getAction("Note")).setMnemonic('n');
		annotationMenu.add(genericDocViewer.getAction("Pen")).setMnemonic('p');
		annotationMenu.add(genericDocViewer.getAction("Rect")).setMnemonic('r');
		annotationMenu.add(genericDocViewer.getAction("Stamp")).setMnemonic('s');
		annotationMenu.add(genericDocViewer.getAction("Text")).setMnemonic('t');
		annotationMenu.addSeparator();
		annotationMenu.add(genericDocViewer.getAction("eraser")).setMnemonic('e');
		annotationMenu.add(genericDocViewer.getAction("move_front")).setMnemonic('f');
		annotationMenu.add(genericDocViewer.getAction("send_back")).setMnemonic('b');
		annotationMenu.add(genericDocViewer.getAction("properties")).setMnemonic('o');
	}
	boolean giveExport = false;
	
	private  CMBDocument document = null;
	
	/** Opens a document in the viewer.  The filename of the document is either passed in (originally
	 * coming from the program arguments) or is prompted for. */
	public void openDocument(String filename,boolean activate) {

		// Make sure the document exists
		FileInputStream inStream;
		try {
			inStream = new FileInputStream(filename);
		} catch (FileNotFoundException fnfe) {
			JOptionPane.showMessageDialog(
				genericDocViewer,
				"File not found:" + filename);
			return;
		}

		// Determine the mimetype for the document
		String mimetype = getMimeType(getExtension(filename));
		
		// If the file type is plain text, prompt for the encoding
		String charset = promptForEncoding(filename,mimetype);

		// Determine if the document is multiple parts.  (A convention is followed for naming
		// the document parts.  If the file is named <name>.<ext>, the parts will be named
		// <name>1.<ext>, <name>2.<ext>, etc.
		int nParts = 1;
		File partfile =
			new File(
				getFileNameWithPath(filename)
					+ nParts
					+ "."
					+ getExtension(filename));
		while (partfile.exists()) {
			nParts++;
			partfile =
				new File(
					getFileNameWithPath(filename)
						+ nParts
						+ "."
						+ getExtension(filename));
		}

		// Determine if an annotation file exists.  This file has a "t_l" suffix by convention.  (This
		// convention is for compatibility with the Windows client and other viewers.
		String annoFilename = getFileNameWithPath(filename) + ".t_l";
		FileInputStream annoStream = null;
		try {
			annoStream = new FileInputStream(annoFilename);
		} catch (Exception e) {
			annoStream = null;
		}

	
		try {

			// Load the document into document services.  Document services is the non-visual layer
			// of the viewer and provides a model for working with the document (either visually or
			// nonvisually.
			document =
				docServices.loadDocument(
					inStream,
					nParts,
					mimetype,
					mimetype,
					null,
					null,
					charset);
		
			int position = document.getAnnotationPosition();
			
		

			// Load the annotations into annotation services.  Annotation services provides a model
			// onto the annotation set for a document and allows the annotations to be manipulated
			// both visually and nonvisually.
			CMBAnnotationSet annotationSet =annoServices.loadAnnotationSet(
					annoStream,	"application/vnd.ibm.modcap",position,1,0);

			// Add the document to a hashtable relating document to the original filename.
			// This is used for save and for callbacks from the viewer to get additional document
			// parts (which are maintained as other files with similar file names to the source document's file).
			docToFilename.put(document, filename);

			// Show the document in the generic doc viewer.  The generic doc viewer is the visual
			// component for the viewer.
			genericDocViewer.showDocument(document, annotationSet, filename,activate);
			
		} catch (Exception e) {
			e.printStackTrace();
			try {   // if document was loaded attempt to drop it.  Ignore errors as this might fail
				if (document != null) {
					docServices.dropDocument(document);
				}
			} catch (Throwable e2) {
			}
			/*JOptionPane.showMessageDialog(
				this,
				"File "
					+ filename
					+ " cannot be opened due to the following error: \n"
					+ "   "
					+ e.getMessage());*/
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			try {   // if document was loaded attempt to drop it.  Ignore errors as this might fail
				if (document != null) {
					docServices.dropDocument(document);
				}
			} catch (Throwable e2) {
			}
			/*JOptionPane.showMessageDialog(
				this,
				"There is not enough memory to display file "
					+ filename
					);*/
		}
	}
	
	
	
	public void cierraDocumento(){
		try {   // if document was loaded attempt to drop it.  Ignore errors as this might fail
			
			docServices.dropAllDocuments();
				/*docServices.dropDocument(document);
				document=null;*/
			}
		 catch (Throwable e2) {
		}
	}

	
	
	/**
	 * Prompt if necessary for the character encoding of a file.  If the
	 * file's type would not warrant asking for the encoding, just return null.
	 */
	private String promptForEncoding(String filename, String mimetype) {
		if (mimetype.equals("text/plain")) {
			String encoding = null;
			/*String encoding = (String)JOptionPane.showInputDialog(
					this,"Specify the character encoding of "+filename+":",
					"Character Encoding",JOptionPane.INFORMATION_MESSAGE,null,
					CHARACTER_ENCODINGS, DEFAULT_CHARACTER_ENCODING);*/
			if (encoding == null) {
				return null;
			}
			// translate the human readable encoding name to the Java charset name
			for (int i = 0; i < CHARACTER_ENCODINGS.length; i++) {
				if (encoding.equals(CHARACTER_ENCODINGS[i])) {
					return CHARSET_NAMES[i];
				}
			}
		}
		return null;
	}

	/**
	 * Saves the document and annotations.  Either or both may be saved, depending on what has
	 * been modified.  If the saveAsNew property on the event is true, the user is prompted
	 * to provide a new file name to save the document as.
	 */
	public void genericDocSave(CMBGenericDocSaveEvent evt) {
		CMBDocument document = evt.getDocument();
		String docSaveFileName = (String) docToFilename.get(document);
		File tempFile = null;
		try {
			if (evt.getSaveAsNew() || document.isModified()) {
				if (evt.getSaveAsNew()) {
					docSaveFileName =
						JOptionPane.showInputDialog(
							"Enter the name of the file to save the document:");
				}
				if (docSaveFileName == null) { // user cancelled
					return;
				}
				// set preferred formats to only one -- the original document format
				// in order to save the document in original doc format
				docServices.setPreferredFormats(
					new String[] { document.getMimeType()});

				if (document.getCanWrite()) {
					// Create a temporary file named <docSavefileName>.tmp in the
					// user's temp directory
					tempFile =
						File.createTempFile(
							"save" + getName(docSaveFileName),
							null);
					FileOutputStream fout = new FileOutputStream(tempFile);
					document.write(fout);
					fout.flush();
					fout.close();
					// Now that write was successful copy the tempFile to document's
					// original file
					FileInputStream finTemp = new FileInputStream(tempFile);
					FileOutputStream foutSave =
						new FileOutputStream(docSaveFileName);
					byte[] copyBuffer = new byte[65536];
					int bytes_read = finTemp.read(copyBuffer);
					while (bytes_read != -1) {
						foutSave.write(copyBuffer,0,bytes_read);
						bytes_read = finTemp.read(copyBuffer);
					}
					foutSave.flush();
					finTemp.close();
					foutSave.close();

					document.setModified(false);
					document.setNew(false);
					genericDocViewer.setDocName(document, docSaveFileName);
				} else {
					/*JOptionPane.showMessageDialog(
						this,
						"The documents of mime type "
							+ document.getMimeType()
							+ " can not be saved.");*/
					return;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			/*JOptionPane.showMessageDialog(
				this,
				"The document was not saved successfully.  "+e.getMessage());*/
			return;
		} finally {
			// If the tempFile was created, delete it
			if (tempFile != null)
				tempFile.delete();
		}
		try {
			CMBAnnotationSet annoSet = evt.getAnnotationSet();
			if (annoSet.isDirty()) {
			    // delete any existing annotations file
			    File annotationFile =
			        new File(getFileNameWithPath(docSaveFileName) + ".t_l");
			    if (annotationFile.exists()) {
			        annotationFile.delete();
			    }
			}
			if (evt.getSaveAsNew() || annoSet.isDirty()) {
				String annoSaveFileName =
					getFileNameWithPath(docSaveFileName) + ".t_l";
				FileOutputStream fout = new FileOutputStream(annoSaveFileName);
				annoSet.write(fout);
				fout.flush();
				fout.close();
				annoSet.setDirty(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			/*JOptionPane.showMessageDialog(
				this,
				"The annotations were not saved successfully.");*/
		}
	}

	/**
	 * Handles the closing of the document in the viewer.
	 */
	public void genericDocClosed(CMBGenericDocClosedEvent evt) {
		CMBDocument document = evt.getDocument();
		docToFilename.remove(document);
	}

	/**
	 * @see com.ibm.mm.viewer.CMBGenericDocHelpListener#onHelp(CMBGenericDocHelpEvent)
	 */
	public void onHelp(CMBGenericDocHelpEvent evt) {
		/*JOptionPane.showMessageDialog(
			this,
			"IBM DB2 Content Manager\n   Java Document Viewer Sample\n      Version 8.3");*/
	}

	/**
	 * This class handles the Open action.  This is a custom action
	 * added to the operations toolbar.  It must be static since it is
	 * instantiated from within CMBGenericDocViewer.
	 */
	public static class OpenAction extends CMBViewerAction {
		private File lastOpenDir;
		public OpenAction(CMBGenericDocViewer viewer) {
			super(viewer);
		}
		public OpenAction(CMBGenericDocViewer viewer, String name) {
			super(viewer, name);
		}
		public OpenAction(CMBGenericDocViewer viewer, String name, Icon icon) {
			super(viewer, name, icon);
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// Find the TGenericDocViewer instance (it is a parent of CMBGenericDocViewer)
			/*TGenericDocViewer gdvFrame =
				(TGenericDocViewer) getViewer()
					.getParent()
					.getParent()
					.getParent()
					.getParent();*/
			JFileChooser chooser = 
				new JFileChooser(System.getProperty("user.dir"));
			chooser.setComponentOrientation(ComponentOrientation.getOrientation(Locale.getDefault()));
			if (lastOpenDir != null) {
				chooser.setCurrentDirectory(lastOpenDir);
			}
			chooser.setMultiSelectionEnabled(true);
			//int ret = chooser.showOpenDialog(gdvFrame);
			lastOpenDir = chooser.getCurrentDirectory();
			/*if (ret == JFileChooser.APPROVE_OPTION) {
				File [] files = chooser.getSelectedFiles();
				for (int i = 0; i < files.length; i++) {
					gdvFrame.openDocument(files[i].getAbsolutePath(),i == 0);
				}
			}*/
		}

	}

	/** This class handles callbacks from CMBStreamingDocServices.  The callbacks request
	 * additional parts of the document and also are used for querying print privilege and
	 * tracing. */
	private class StreamingDocServicesCallbacks
		extends CMBStreamingDocServicesCallbacks
		implements Serializable {
		StreamingDocServicesCallbacks() {
		}

		/** Retrieve a background form. */
		public InputStream getForm(CMBDocument document, String formName) {
			String directory =
				getDirectory((String) docToFilename.get(document));
			System.out.println(
				"Document Engine requesting forms: "
					+ directory
					+ File.separator
					+ formName);
			try {
				FileInputStream inStream =
					new FileInputStream(directory + File.separator + formName);
				return inStream;
			} catch (IOException e) {
			}

			return null;
		}

		/** Retrieve a document part. */
		public InputStream getPart(
			CMBDocument document,
			int partNumber,
			StringBuffer outMimeType) {
			String filename = (String) docToFilename.get(document);
			String partName =
				getFileNameWithPath(filename)
					+ partNumber
					+ "."
					+ getExtension(filename);
			System.out.println(
				"Document Engine requesting part number:" + partName);
			try {
				FileInputStream inStream = new FileInputStream(partName);
				return inStream;
			} catch (IOException e) {
			}
			return null;
		}
		
		/** Retrieve the print privilege for the document. */
		public boolean getPrintPrivilege(CMBDocument document) {
			return true;
		}

		/** Retrieve a document annotation. */
		public InputStream getAnnotation(
			CMBDocument document,
			int annotationNumber) {
			System.out.println("Document Engine requesting annotation");
			return null;
		}

		/** Retrieve a document's resources. */
		public InputStream getResources(CMBDocument document) {
			System.out.println("Document Engine requesting resources");
			return null;
		}

		/** Returns true if tracing is enabled */
		public boolean traceEnabled() {
			return traceEnabled;
		}

		/** Writes a trace message. */
		public void trace(String message) {
			if (traceEnabled) {
				System.out.println("TGenericDocViewer:" + message);
			}
		}

		/** Retrieve the character encoding for a document part. */
		public String getPartEncoding(CMBDocument document, int partNumber) {
			String filename = (String) docToFilename.get(document);
			String partName =
				getFileNameWithPath(filename)
					+ partNumber
					+ "."
					+ getExtension(filename);
			System.out.println(
				"Document Engine requesting character encoding for part number:" + partName);
			return promptForEncoding(partName,getMimeType(filename));
		}
	}

	/**
	 * AnnotationServicesCallbacks class handles callbacks from CMBAnnotationServices.
	 * Note: In 8.2, annotation services callbacks were used to save annotations to the server
	 * (or in the case of this sample, the file system).  For 8.3, a new event, CMBGenericDocSaveEvent,
	 * was added.  This sample has been updated to listen for this event and save both annotations
	 * and modified documents (through page manipulation).  Therefore, some of the callbacks below
	 * are no longer used.
	 */
	private class AnnotationServicesCallbacks
		extends CMBAnnotationServicesCallbacks
		implements Serializable {
		AnnotationServicesCallbacks() {
		}

		/** Retrieves a document annotation. */
		public InputStream getAnnotationPart(
			CMBAnnotationSet annoSet,
			int annotationNumber)
			throws CMBAnnotationEngineException {
			// Note: This method will never be called because currently no annotation
			//           engines save annotations as multiple parts.
			System.out.println(
				"Annotation Engine requesting additional annotation parts");
			return null;
		}
		/** Retrieves the privilege for an annotation. */
		public boolean getPrivilege(CMBAnnotationSet annoSet, int privilegeID)
			throws CMBAnnotationEngineException {
			return true;
		}

		/** Saves the annotation part. */
		public int addAnnotationPart(
			CMBAnnotationSet annotationSet,
			byte[] annotationData)
			throws CMBAnnotationEngineException {
			// Note: This method should never be called because annotations saving is performed
			//           by the genericDocSave method.
			System.out.println(
				"Annotation Engine requesting add of an annotation part");
			return -1; // indicates save failed
		}

		/** Updates a page annotation. */
		public void updateAnnotationPart(
			CMBAnnotationSet annotationSet,
			byte[] annotationData,
			int annotationNumber)
			throws CMBAnnotationEngineException {
			// Note: This method should never be called because annotations saving is performed
			//           by the genericDocSave method.
			System.out.println(
				"Annotation Engine requesting update of an annotation part");
		}

		/** Removes an annotation part. */
		public void removeAnnotationPart(
			CMBAnnotationSet annotationSet,
			int annotationNumber)
			throws CMBAnnotationEngineException {
			// Note: This method should never be called because annotations saving is performed
			//           by the genericDocSave method.
			System.out.println(
				"Annotation Engine requesting remove of an annotation part");
		}

		/** Returns true if tracing is enabled */
		public boolean traceEnabled() {
			return traceEnabled;
		}

		/** Writes a trace message. */
		public void trace(String message) {
			if (traceEnabled) {
				System.out.println(message);
			}
		}

	} // end of AnnotationServicesCallbacks class

	/** The status area in this sample displays the current page, total pages, and current scale. */
	private class StatusArea extends JPanel	implements CMBGenericDocStateChangedListener, CMBGenericDocClosedListener,CMBGenericDocSelectedListener {
		JLabel pageIdentifier = new JLabel();
		public StatusArea() {
			super();
			add(pageIdentifier);
		}
		/**
		 * @see com.ibm.mm.viewer.CMBGenericDocStateChangedListener#genericDocStateChanged(CMBGenericDocStateChangedEvent)
		 */
		public void genericDocStateChanged(CMBGenericDocStateChangedEvent evt) {
			updateStatusArea();
		}

		/**
		 * @see com.ibm.mm.viewer.CMBGenericDocClosedListener#genericDocClosed(CMBGenericDocClosedEvent)
		 */
		public void genericDocClosed(CMBGenericDocClosedEvent evt) {
			updateStatusArea();
		}

		public void genericDocSelected(CMBGenericDocSelectedEvent evt) {
			updateStatusArea();
		}

		private void updateStatusArea() {
			if (genericDocViewer.getSelectedDocument() != null) {
				pageIdentifier.setText(
					"Page "
						+ genericDocViewer.getCurrentPageNumber()
						+ " of "
						+ genericDocViewer.getPageCount()
						+ "               Scale: "
						+ ((int) (genericDocViewer.getScale() * 100))
						+ "%");
			} else {
				pageIdentifier.setText("");
			}
		}

	} // end of StatusArea class

}
