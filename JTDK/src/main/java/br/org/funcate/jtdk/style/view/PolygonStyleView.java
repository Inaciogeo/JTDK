package br.org.funcate.jtdk.style.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.PlainDocument;

import br.org.funcate.jtdk.style.constant.PreviewConstants;
import br.org.funcate.jtdk.style.model.DocumentFilterComER;
import br.org.funcate.jtdk.style.view.adapter.PolygonStyleAdapter;
import br.org.funcate.jtdk.util.ImageIconLoader;

@SuppressWarnings("serial")
public class PolygonStyleView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStyleName;
	private JPanel pnlArea;
	@SuppressWarnings("rawtypes")
	private JComboBox cboAreaTransparency;
	@SuppressWarnings("rawtypes")
	private JComboBox cboAreaStyle;
	private JTextField txtFile;
	private JButton btnFile;
	private JTextField txtHeight;
	private JTextField txtContourWidth;
	@SuppressWarnings("rawtypes")
	private JComboBox cboContourStyle;
	private JCheckBox chkbUseImage;
	private JTable tblVisual;
	private PreviewCanvas canvasPreview;
	private JScrollPane scrollVisual;
	private JButton btnAddVisual;
	private JButton btnUpArrow;
	private JButton btnDownArrow;
	private JButton btnDeleteVisual;
	private JButton btnCopyVisual;
	private JButton btnSave;
	private JButton btnCancel;
	private JPanel pnlAreaColor;
	@SuppressWarnings("rawtypes")
	private JComboBox cboContourFinal;
	@SuppressWarnings("rawtypes")
	private JComboBox cboContourJoin;
	private JPanel pnlContourColor;
	private JButton btnContourColor;
	private JButton btnAreaColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
		} catch (IllegalAccessException ex) {
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
		}
		try {
			PolygonStyleView dialog = new PolygonStyleView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("rawtypes")
	public PolygonStyleView() {
		setTitle("Estilo de Pol\u00EDgonos");
		setResizable(false);
		setBounds(100, 100, 460, 571);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblStyleName = new JLabel("Nome do Estilo:");

		txtStyleName = new JTextField();
		txtStyleName.setColumns(10);

		JTabbedPane tpnlConfigPolygon = new JTabbedPane(JTabbedPane.TOP);
		tpnlConfigPolygon.setBorder(new LineBorder(new Color(0, 0, 0)));

		JPanel pnlVisual = new JPanel();
		pnlVisual.setBorder(new TitledBorder(null, "Visuais", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel pnlPreview = new JPanel();
		pnlPreview.setBorder(new TitledBorder(null, "Pr\u00E9-visualiza\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_contentPanel
										.createParallelGroup(Alignment.TRAILING)
										.addComponent(tpnlConfigPolygon, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
										.addGroup(
												gl_contentPanel.createSequentialGroup().addComponent(lblStyleName)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(txtStyleName, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
										.addGroup(
												gl_contentPanel
														.createSequentialGroup()
														.addComponent(pnlVisual, GroupLayout.PREFERRED_SIZE, 207,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(pnlPreview, GroupLayout.PREFERRED_SIZE, 206,
																GroupLayout.PREFERRED_SIZE))).addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_contentPanel
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblStyleName)
										.addComponent(txtStyleName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tpnlConfigPolygon, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(pnlPreview, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
										.addComponent(pnlVisual, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));

		canvasPreview = new PreviewCanvas(PreviewConstants.POLYGON, null);
		canvasPreview.setBackground(Color.WHITE);
		GroupLayout gl_pnlPreview = new GroupLayout(pnlPreview);
		gl_pnlPreview.setHorizontalGroup(gl_pnlPreview.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlPreview.createSequentialGroup().addGap(22)
						.addComponent(canvasPreview, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE)));
		gl_pnlPreview.setVerticalGroup(gl_pnlPreview.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlPreview.createSequentialGroup()
						.addComponent(canvasPreview, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlPreview.setLayout(gl_pnlPreview);

		scrollVisual = new JScrollPane();

		btnAddVisual = new JButton("");
		btnAddVisual.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/add.gif", PolygonStyleView.class));

		btnUpArrow = new JButton("");
		btnUpArrow.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/upArrow.png", PolygonStyleView.class));

		btnDownArrow = new JButton("");
		btnDownArrow.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/downArrow.png", PolygonStyleView.class));

		btnDeleteVisual = new JButton("");
		btnDeleteVisual.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/delete.gif", PolygonStyleView.class));

		btnCopyVisual = new JButton("");
		btnCopyVisual.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/copy.gif", PolygonStyleView.class));
		GroupLayout gl_pnlVisual = new GroupLayout(pnlVisual);
		gl_pnlVisual
				.setHorizontalGroup(gl_pnlVisual
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_pnlVisual
										.createSequentialGroup()
										.addGroup(
												gl_pnlVisual
														.createParallelGroup(Alignment.LEADING)
														.addGroup(
																gl_pnlVisual
																		.createParallelGroup(Alignment.LEADING)
																		.addGroup(
																				gl_pnlVisual
																						.createParallelGroup(Alignment.LEADING)
																						.addGroup(
																								gl_pnlVisual
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addGroup(
																												gl_pnlVisual
																														.createSequentialGroup()
																														.addContainerGap(
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																btnAddVisual)
																														.addPreferredGap(
																																ComponentPlacement.RELATED))
																										.addGroup(
																												gl_pnlVisual
																														.createSequentialGroup()
																														.addContainerGap()
																														.addComponent(
																																btnUpArrow)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)))
																						.addGroup(
																								gl_pnlVisual
																										.createSequentialGroup()
																										.addContainerGap()
																										.addComponent(btnDownArrow)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)))
																		.addGroup(
																				gl_pnlVisual.createSequentialGroup().addContainerGap()
																						.addComponent(btnDeleteVisual)
																						.addPreferredGap(ComponentPlacement.RELATED)))
														.addGroup(
																gl_pnlVisual.createSequentialGroup().addContainerGap()
																		.addComponent(btnCopyVisual)
																		.addPreferredGap(ComponentPlacement.RELATED)))
										.addComponent(scrollVisual, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)));
		gl_pnlVisual.setVerticalGroup(gl_pnlVisual
				.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollVisual, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
				.addGroup(
						gl_pnlVisual.createSequentialGroup().addContainerGap().addComponent(btnAddVisual)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnUpArrow)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnDownArrow)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnDeleteVisual)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnCopyVisual)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		tblVisual = new JTable();
		scrollVisual.setViewportView(tblVisual);
		pnlVisual.setLayout(gl_pnlVisual);

		pnlArea = new JPanel();
		tpnlConfigPolygon.addTab("\u00C1rea", null, pnlArea, null);

		btnAreaColor = new JButton("Cor...");
		btnAreaColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		pnlAreaColor = new JPanel();
		pnlAreaColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlAreaColor.setBackground(Color.YELLOW);

		JLabel lblTransparency = new JLabel("Transpar\u00EAncia (%):");

		cboAreaTransparency = new JComboBox();

		JLabel lblAreaStyle = new JLabel("Estilo:");

		cboAreaStyle = new JComboBox();

		chkbUseImage = new JCheckBox("Utilizar imagem");

		txtFile = new JTextField();
		txtFile.setEnabled(false);
		txtFile.setColumns(10);

		btnFile = new JButton("Arquivo...");

		JLabel lblHeight = new JLabel("Altura (mm):");

		txtHeight = new JTextField();
		txtHeight.setText("10.00");
		txtHeight.setEnabled(false);
		txtHeight.setColumns(10);
		GroupLayout gl_pnlArea = new GroupLayout(pnlArea);
		gl_pnlArea.setHorizontalGroup(gl_pnlArea.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlArea
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnlArea.createParallelGroup(Alignment.TRAILING).addComponent(lblHeight).addComponent(lblAreaStyle)
										.addComponent(lblTransparency).addComponent(btnAreaColor).addComponent(btnFile))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlArea.createParallelGroup(Alignment.LEADING).addComponent(chkbUseImage)
										.addComponent(pnlAreaColor, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
										.addComponent(cboAreaStyle, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
										.addComponent(cboAreaTransparency, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtFile, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
										.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		gl_pnlArea.setVerticalGroup(gl_pnlArea.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlArea
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnlArea.createParallelGroup(Alignment.TRAILING)
										.addComponent(pnlAreaColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnAreaColor))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlArea
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTransparency)
										.addComponent(cboAreaTransparency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlArea
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblAreaStyle)
										.addComponent(cboAreaStyle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(chkbUseImage)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlArea
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE).addComponent(btnFile))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlArea
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblHeight)
										.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)).addGap(19)));
		pnlArea.setLayout(gl_pnlArea);

		JPanel pnlContour = new JPanel();
		tpnlConfigPolygon.addTab("Contorno", null, pnlContour, null);

		btnContourColor = new JButton("Cor...");

		pnlContourColor = new JPanel();
		pnlContourColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlContourColor.setBackground(Color.RED);

		JLabel lblContourWidth = new JLabel("Largura (mm):");

		PlainDocument doc = new PlainDocument();
		doc.setDocumentFilter(new DocumentFilterComER("[^0-9|^\\.]"));

		txtContourWidth = new JTextField();
		txtContourWidth.setDocument(doc);
		txtContourWidth.setText("1.00");
		txtContourWidth.setColumns(10);

		JLabel lblContourStyle = new JLabel("Estilo:");

		cboContourStyle = new JComboBox();

		JLabel lblContourFinal = new JLabel("Final da Linha:");

		cboContourFinal = new JComboBox();

		JLabel lblContourJoin = new JLabel("Jun\u00E7\u00E3o da Linha:");

		cboContourJoin = new JComboBox();
		GroupLayout gl_pnlContour = new GroupLayout(pnlContour);
		gl_pnlContour.setHorizontalGroup(gl_pnlContour.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlContour
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnlContour.createParallelGroup(Alignment.TRAILING).addComponent(lblContourStyle)
										.addComponent(lblContourWidth).addComponent(lblContourFinal).addComponent(lblContourJoin)
										.addComponent(btnContourColor))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlContour.createParallelGroup(Alignment.LEADING)
										.addComponent(pnlContourColor, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
										.addComponent(cboContourStyle, 0, 323, Short.MAX_VALUE)
										.addComponent(txtContourWidth, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
										.addComponent(cboContourFinal, 0, 321, Short.MAX_VALUE)
										.addComponent(cboContourJoin, 0, 308, Short.MAX_VALUE)).addContainerGap()));
		gl_pnlContour.setVerticalGroup(gl_pnlContour.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlContour
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnlContour.createParallelGroup(Alignment.LEADING)
										.addComponent(pnlContourColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnContourColor))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlContour
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblContourWidth)
										.addComponent(txtContourWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlContour
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblContourStyle)
										.addComponent(cboContourStyle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlContour
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblContourFinal)
										.addComponent(cboContourFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlContour
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblContourJoin)
										.addComponent(cboContourJoin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)).addContainerGap(43, Short.MAX_VALUE)));
		pnlContour.setLayout(gl_pnlContour);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSave = new JButton("Salvar");
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				btnCancel = new JButton("Cancelar");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}

		this.addAdapter();
	}

	private void addAdapter() {
		PolygonStyleAdapter adapter = new PolygonStyleAdapter(this);
		this.btnAreaColor.addActionListener(adapter);
		this.btnContourColor.addActionListener(adapter);
		this.btnCancel.addActionListener(adapter);
		this.btnAddVisual.addActionListener(adapter);
		this.btnCopyVisual.addActionListener(adapter);
		this.btnDeleteVisual.addActionListener(adapter);
		this.btnUpArrow.addActionListener(adapter);
		this.btnDownArrow.addActionListener(adapter);
		this.btnFile.addActionListener(adapter);
		this.btnSave.addActionListener(adapter);
		this.tblVisual.getSelectionModel().addListSelectionListener(adapter);
		this.txtContourWidth.addCaretListener(adapter);
		this.cboAreaStyle.addActionListener(adapter);
		this.cboAreaTransparency.addActionListener(adapter);
		this.cboContourFinal.addActionListener(adapter);
		this.cboContourJoin.addActionListener(adapter);
		this.cboContourStyle.addActionListener(adapter);
	}

	/**
	 * @return the txtStyleName
	 */
	public JTextField getTxtStyleName() {
		return txtStyleName;
	}

	/**
	 * @param txtStyleName
	 *            the txtStyleName to set
	 */
	public void setTxtStyleName(JTextField txtStyleName) {
		this.txtStyleName = txtStyleName;
	}

	/**
	 * @return the cboAreaTransparency
	 */
	@SuppressWarnings("rawtypes")
	public JComboBox getCboAreaTransparency() {
		return cboAreaTransparency;
	}

	/**
	 * @param cboAreaTransparency
	 *            the cboAreaTransparency to set
	 */
	@SuppressWarnings("rawtypes")
	public void setCboAreaTransparency(JComboBox cboAreaTransparency) {
		this.cboAreaTransparency = cboAreaTransparency;
	}

	/**
	 * @return the cboAreaStyle
	 */
	@SuppressWarnings("rawtypes")
	public JComboBox getCboAreaStyle() {
		return cboAreaStyle;
	}

	/**
	 * @param cboAreaStyle
	 *            the cboAreaStyle to set
	 */
	@SuppressWarnings("rawtypes")
	public void setCboAreaStyle(JComboBox cboAreaStyle) {
		this.cboAreaStyle = cboAreaStyle;
	}

	/**
	 * @return the txtFile
	 */
	public JTextField getTxtFile() {
		return txtFile;
	}

	/**
	 * @param txtFile
	 *            the txtFile to set
	 */
	public void setTxtFile(JTextField txtFile) {
		this.txtFile = txtFile;
	}

	/**
	 * @return the btnFile
	 */
	public JButton getBtnFile() {
		return btnFile;
	}

	/**
	 * @param btnFile
	 *            the btnFile to set
	 */
	public void setBtnFile(JButton btnFile) {
		this.btnFile = btnFile;
	}

	/**
	 * @return the txtHeight
	 */
	public JTextField getTxtHeight() {
		return txtHeight;
	}

	/**
	 * @param txtHeight
	 *            the txtHeight to set
	 */
	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	/**
	 * @return the txtContourWidth
	 */
	public JTextField getTxtContourWidth() {
		return txtContourWidth;
	}

	/**
	 * @param txtContourWidth
	 *            the txtContourWidth to set
	 */
	public void setTxtContourWidth(JTextField txtContourWidth) {
		this.txtContourWidth = txtContourWidth;
	}

	/**
	 * @return the cboContourStyle
	 */
	@SuppressWarnings("rawtypes")
	public JComboBox getCboContourStyle() {
		return cboContourStyle;
	}

	/**
	 * @param cboContourStyle
	 *            the cboContourStyle to set
	 */
	@SuppressWarnings("rawtypes")
	public void setCboContourStyle(JComboBox cboContourStyle) {
		this.cboContourStyle = cboContourStyle;
	}

	/**
	 * @return the chkbUseImage
	 */
	public JCheckBox getChkbUseImage() {
		return chkbUseImage;
	}

	/**
	 * @param chkbUseImage
	 *            the chkbUseImage to set
	 */
	public void setChkbUseImage(JCheckBox chkbUseImage) {
		this.chkbUseImage = chkbUseImage;
	}

	/**
	 * @return the tblVisual
	 */
	public JTable getTblVisual() {
		return tblVisual;
	}

	/**
	 * @param tblVisual
	 *            the tblVisual to set
	 */
	public void setTblVisual(JTable tblVisual) {
		this.tblVisual = tblVisual;
	}

	/**
	 * @return the canvasPreview
	 */
	public PreviewCanvas getCanvasPreview() {
		return canvasPreview;
	}

	/**
	 * @param canvasPreview
	 *            the canvasPreview to set
	 */
	public void setCanvasPreview(PreviewCanvas canvasPreview) {
		this.canvasPreview = canvasPreview;
	}

	/**
	 * @return the btnAddVisual
	 */
	public JButton getBtnAddVisual() {
		return btnAddVisual;
	}

	/**
	 * @param btnAddVisual
	 *            the btnAddVisual to set
	 */
	public void setBtnAddVisual(JButton btnAddVisual) {
		this.btnAddVisual = btnAddVisual;
	}

	/**
	 * @return the btnUpArrow
	 */
	public JButton getBtnUpArrow() {
		return btnUpArrow;
	}

	/**
	 * @param btnUpArrow
	 *            the btnUpArrow to set
	 */
	public void setBtnUpArrow(JButton btnUpArrow) {
		this.btnUpArrow = btnUpArrow;
	}

	/**
	 * @return the btnDownArrow
	 */
	public JButton getBtnDownArrow() {
		return btnDownArrow;
	}

	/**
	 * @param btnDownArrow
	 *            the btnDownArrow to set
	 */
	public void setBtnDownArrow(JButton btnDownArrow) {
		this.btnDownArrow = btnDownArrow;
	}

	/**
	 * @return the btnDeleteVisual
	 */
	public JButton getBtnDeleteVisual() {
		return btnDeleteVisual;
	}

	/**
	 * @param btnDeleteVisual
	 *            the btnDeleteVisual to set
	 */
	public void setBtnDeleteVisual(JButton btnDeleteVisual) {
		this.btnDeleteVisual = btnDeleteVisual;
	}

	/**
	 * @return the btnCopyVisual
	 */
	public JButton getBtnCopyVisual() {
		return btnCopyVisual;
	}

	/**
	 * @param btnCopyVisual
	 *            the btnCopyVisual to set
	 */
	public void setBtnCopyVisual(JButton btnCopyVisual) {
		this.btnCopyVisual = btnCopyVisual;
	}

	/**
	 * @return the btnSave
	 */
	public JButton getBtnSave() {
		return btnSave;
	}

	/**
	 * @param btnSave
	 *            the btnSave to set
	 */
	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	/**
	 * @return the btnCancel
	 */
	public JButton getBtnCancel() {
		return btnCancel;
	}

	/**
	 * @param btnCancel
	 *            the btnCancel to set
	 */
	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	/**
	 * @return the pnlAreaColor
	 */
	public JPanel getPnlAreaColor() {
		return pnlAreaColor;
	}

	/**
	 * @param pnlAreaColor
	 *            the pnlAreaColor to set
	 */
	public void setPnlAreaColor(JPanel pnlAreaColor) {
		this.pnlAreaColor = pnlAreaColor;
	}

	/**
	 * @return the cboContourFinal
	 */
	@SuppressWarnings("rawtypes")
	public JComboBox getCboContourFinal() {
		return cboContourFinal;
	}

	/**
	 * @param cboContourFinal
	 *            the cboContourFinal to set
	 */
	@SuppressWarnings("rawtypes")
	public void setCboContourFinal(JComboBox cboContourFinal) {
		this.cboContourFinal = cboContourFinal;
	}

	/**
	 * @return the cboContourJoin
	 */
	@SuppressWarnings("rawtypes")
	public JComboBox getCboContourJoin() {
		return cboContourJoin;
	}

	/**
	 * @param cboContourJoin
	 *            the cboContourJoin to set
	 */
	@SuppressWarnings("rawtypes")
	public void setCboContourJoin(JComboBox cboContourJoin) {
		this.cboContourJoin = cboContourJoin;
	}

	/**
	 * @return the pnlContourColor
	 */
	public JPanel getPnlContourColor() {
		return pnlContourColor;
	}

	/**
	 * @param pnlContourColor
	 *            the pnlContourColor to set
	 */
	public void setPnlContourColor(JPanel pnlContourColor) {
		this.pnlContourColor = pnlContourColor;
	}

	/**
	 * @return the btnContourColor
	 */
	public JButton getBtnContourColor() {
		return btnContourColor;
	}

	/**
	 * @param btnContourColor
	 *            the btnContourColor to set
	 */
	public void setBtnContourColor(JButton btnContourColor) {
		this.btnContourColor = btnContourColor;
	}

	/**
	 * @return the btnAreaColor
	 */
	public JButton getBtnAreaColor() {
		return btnAreaColor;
	}

	/**
	 * @param btnAreaColor
	 *            the btnAreaColor to set
	 */
	public void setBtnAreaColor(JButton btnAreaColor) {
		this.btnAreaColor = btnAreaColor;
	}
}
