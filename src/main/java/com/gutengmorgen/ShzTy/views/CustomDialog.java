package com.gutengmorgen.ShzTy.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.lang.reflect.Field;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.gutengmorgen.ShzTy.controller.MainController;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoReturnArtist;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.services.GenreService;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.AbstractListModel;

public class CustomDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    private final JPanel cPanel = new JPanel();
    private final GridBagConstraints constraints = new GridBagConstraints();
    private JButton okButton;
    private JButton cancelButton;

    private int columnIndex = 0;
    private int rowIndex = 0;
    private JScrollPane scrollPane;
    private JList list_1;

    public static void main(String[] args) {
	try {
	    CustomDialog dialog = new CustomDialog("Custom Dialog");
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//	    dialog.autoFill(DtoCreateAlbum.class);
	    dialog.setVisible(true);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public CustomDialog(String title) {
	setTitle(title);
	setBounds(100, 100, 480, 175);

	cPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(cPanel, BorderLayout.CENTER);
	GridBagLayout gbl_cPanel = new GridBagLayout();
	gbl_cPanel.columnWidths = new int[]{0, 0, 145};
	gbl_cPanel.rowWeights = new double[]{0.0};
	gbl_cPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
	cPanel.setLayout(gbl_cPanel);
	{
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		cPanel.add(scrollPane, gbc_scrollPane);
		{
			list_1 = new JList();
			list_1.setModel(new AbstractListModel() {
				String[] values = new String[] {"gwgege", "23523534", "tyuy", "jyjykj"};
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			scrollPane.setViewportView(list_1);
		}
	}

	{
	    JPanel buttonPane = new JPanel();
	    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    {
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	    }
	    {
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			dispose();
		    }
		});
		buttonPane.add(cancelButton);
	    }
	}
    }

    public void autoFill(Class<?> classToRead) {
	Field[] fields = classToRead.getDeclaredFields();
	MainController controller = new MainController();

	for (Field field : fields) {
	    String fieldName = field.getName();

	    JLabel label = new JLabel(fieldName);
	    cPanel.add(label);

	    if (fieldName.contains("Id")) {
		// NOTE: fieldType.getSimpleName().equals("int")
//		JTextField textField = new JTextField(10);
//		((AbstractDocument) textField.getDocument()).setDocumentFilter(new FilterOnlyNumbers());
//		contentPanel.add(textField);
		GenreService service = new GenreService();
		List<Genre> list = service.getAllGenres();
		DefaultComboBoxModel<Genre> model = new DefaultComboBoxModel<>();
		for (Genre genre : list) {
		    model.addElement(genre);
		}
		cPanel.add(new JComboBox<>(model));

	    } else if (fieldName.contains("Date")) {
//		JTextField textField = new JTextField(10);
//		((AbstractDocument) textField.getDocument()).setDocumentFilter(new FilterDateFormat());
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		cPanel.add(dateChooser);

	    } else if (fieldName.contains("IDs")) {
		// el controller devolvera una lista de objectos con dos parametros {nombre, id}
		cPanel.add(controller.getGenreScrollList());
	    } else {
		JTextField textField = new JTextField(10);
		cPanel.add(textField);
	    }
	}
    }

    private void addComponent(JComponent component) {
	constraints.gridx = columnIndex;
	constraints.gridy = rowIndex;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets(5, 5, 5, 10);

	cPanel.add(component, constraints);

	columnIndex++;
	if (columnIndex >= 2) {
	    columnIndex = 0;
	    rowIndex++;
	}

	cPanel.revalidate();
	pack();
	cPanel.repaint();
    }

    private void addComponent(String name, JComponent comp) {
	constraints.gridx = 0;
	constraints.gridy = rowIndex;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets(5, 5, 5, 10);
	constraints.fill = GridBagConstraints.NONE;

	cPanel.add(new JLabel(name), constraints);

	constraints.gridx = 1;
	cPanel.add(comp, constraints);

	rowIndex++;

	cPanel.revalidate();
	cPanel.repaint();
    }

    public void autoFillReturn(DtoReturnArtist dto) {
	JLabel ll1 = new JLabel(dto.id().toString());
	addComponent("Id:", ll1);

	JLabel ll2 = new JLabel(dto.name().toString());
	addComponent("Name:", ll2);

	JLabel ll3 = new JLabel(dto.bornDate().toString());
	addComponent("Born Date:", ll3);

	JLabel ll4 = new JLabel(dto.gender());
	addComponent("Gender:", ll4);

	JLabel ll5 = new JLabel(dto.country());
	addComponent("Country:", ll5);

	JLabel ll6 = new JLabel(dto.biography());
	addComponent("Biography:", ll6);

	JScrollPane sll7 = new JScrollPane();
	JList<String> ll7 = new JList<>();
	sll7.setViewportView(ll7);
	
	DefaultListModel<String> ll7m = new DefaultListModel<>();
	ll7m.addAll(dto.albums());
	ll7.setModel(ll7m);
	
	addComponent("Albums:", sll7);

	JLabel ll8 = new JLabel(dto.languages().toString());
	addComponent("Languages:", ll8);

	JLabel ll9 = new JLabel(dto.genres().toString());
	addComponent("Genres:", ll9);

	okButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		dispose();
	    }
	});

	pack();
    }
}
