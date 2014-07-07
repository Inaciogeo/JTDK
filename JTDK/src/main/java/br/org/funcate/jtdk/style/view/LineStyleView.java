package br.org.funcate.jtdk.style.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
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
import br.org.funcate.jtdk.style.model.VisualTableModel;
import br.org.funcate.jtdk.style.view.adapter.LineStyleAdapter;
import br.org.funcate.jtdk.util.ImageIconLoader;

/**
 * This is the Dialog to create a <a
 * href="http://www.opengeospatial.org/standards/sld">OGC-SLD</a> style for
 * {@link LineString}.
 * 
 * 
 * @author Moraes, Emerson Leite.
 * 
 */
@SuppressWarnings("serial")
public class LineStyleView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStyleName;
	private JTextField txtLineWidth;
	private JTable tblVisual;
	private JButton btnUpArrow = new JButton("");
	private JPanel pnlVisual;
	private JPanel pnlPreview;
	private JLabel lblStyleName;
	private PreviewCanvas canvasPreview;
	private JScrollPane scrollVisualPane;
	private JButton btnAdd;
	private JButton btnDownArrow;
	private JButton btnDeleteVisual;
	private JButton btnCopyVisual;
	private JPanel pnlConfigLine;
	private JButton btnColor;
	private JPanel pnlColor;
	private JLabel lblWidth;
	private JLabel lblLineStyle;
	private JComboBox cboLineStyle;
	private JLabel lblFinalLine;
	private JComboBox cboFinalLine;
	private JLabel lblJoinLine;
	private JButton btnSave;
	private JButton btnCancel;
	private JPanel buttonPane;
	private JComboBox cboJoinLine;

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
			LineStyleView dialog = new LineStyleView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LineStyleView() {
		setTitle("Estilo de Linhas");
		setBounds(100, 100, 450, 542);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblStyleName = new JLabel("Nome do Estilo: ");

		txtStyleName = new JTextField();
		txtStyleName.setColumns(10);

		JTabbedPane tpnlConfigLinha = new JTabbedPane(JTabbedPane.TOP);
		tpnlConfigLinha.setBorder(new LineBorder(new Color(0, 0, 0)));

		pnlVisual = new JPanel();
		pnlVisual.setBorder(new TitledBorder(null, "Visuais", TitledBorder.LEFT, TitledBorder.TOP, null, null));

		pnlPreview = new JPanel();
		pnlPreview.setBorder(new TitledBorder(null, "Pr\u00E9-visualiza\u00E7\u00E3o", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_contentPanel
										.createParallelGroup(Alignment.LEADING)
										.addComponent(tpnlConfigLinha)
										.addGroup(
												gl_contentPanel
														.createSequentialGroup()
														.addComponent(pnlVisual, GroupLayout.PREFERRED_SIZE, 202,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(pnlPreview, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGroup(
												gl_contentPanel
														.createSequentialGroup()
														.addComponent(lblStyleName)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(txtStyleName, GroupLayout.PREFERRED_SIZE, 312,
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
						.addComponent(tpnlConfigLinha, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_contentPanel
										.createParallelGroup(Alignment.LEADING)
										.addComponent(pnlPreview, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(pnlVisual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)).addContainerGap()));

		canvasPreview = new PreviewCanvas(PreviewConstants.LINE, null);
		canvasPreview.setBackground(Color.WHITE);
		GroupLayout gl_pnlPreview = new GroupLayout(pnlPreview);
		gl_pnlPreview.setHorizontalGroup(gl_pnlPreview.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_pnlPreview.createSequentialGroup().addContainerGap(32, Short.MAX_VALUE)
						.addComponent(canvasPreview, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE).addGap(27)));
		gl_pnlPreview.setVerticalGroup(gl_pnlPreview.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlPreview.createSequentialGroup().addComponent(canvasPreview, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addContainerGap()));
		pnlPreview.setLayout(gl_pnlPreview);

		scrollVisualPane = new JScrollPane();

		tblVisual = new JTable(new VisualTableModel());
		scrollVisualPane.setViewportView(tblVisual);

		btnAdd = new JButton("");
		btnAdd.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/add.gif", LineStyleView.class));
		btnUpArrow.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/upArrow.png", LineStyleView.class));

		btnDownArrow = new JButton("");
		btnDownArrow.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/downArrow.png", LineStyleView.class));

		btnDeleteVisual = new JButton("");
		btnDeleteVisual.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/delete.gif", LineStyleView.class));

		btnCopyVisual = new JButton("");
		btnCopyVisual.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/copy.gif", LineStyleView.class));
		GroupLayout gl_pnlVisual = new GroupLayout(pnlVisual);
		gl_pnlVisual.setHorizontalGroup(gl_pnlVisual.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_pnlVisual
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnlVisual.createParallelGroup(Alignment.LEADING)
										.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnUpArrow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnDownArrow, Alignment.TRAILING).addComponent(btnDeleteVisual)
										.addComponent(btnCopyVisual)).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollVisualPane, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE).addContainerGap()));
		gl_pnlVisual.setVerticalGroup(gl_pnlVisual
				.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollVisualPane, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
				.addGroup(
						gl_pnlVisual.createSequentialGroup().addComponent(btnAdd).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnUpArrow).addPreferredGap(ComponentPlacement.RELATED).addComponent(btnDownArrow)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnDeleteVisual)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnCopyVisual).addContainerGap()));
		pnlVisual.setLayout(gl_pnlVisual);

		pnlConfigLine = new JPanel();
		tpnlConfigLinha.addTab("Linhas", null, pnlConfigLine, null);

		btnColor = new JButton("Cor...");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		pnlColor = new JPanel();
		pnlColor.setBorder(new LineBorder(new Color(0, 0, 0), 2));

		lblWidth = new JLabel("Espessura:");

		txtLineWidth = new JTextField();

		PlainDocument doc = new PlainDocument();
		doc.setDocumentFilter(new DocumentFilterComER("[^0-9|^\\.]"));
		txtLineWidth.setDocument(doc);

		txtLineWidth.setText("2.00");
		txtLineWidth.setColumns(10);

		lblLineStyle = new JLabel("Estilo:");

		cboLineStyle = new JComboBox();

		lblFinalLine = new JLabel("Final da Linha:");

		cboFinalLine = new JComboBox();

		lblJoinLine = new JLabel("Jun\u00E7\u00E3o da Linha:");

		cboJoinLine = new JComboBox();
		GroupLayout gl_pnlConfigLine = new GroupLayout(pnlConfigLine);
		gl_pnlConfigLine.setHorizontalGroup(gl_pnlConfigLine.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlConfigLine
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnlConfigLine.createParallelGroup(Alignment.TRAILING).addComponent(lblWidth).addComponent(btnColor)
										.addComponent(lblLineStyle).addComponent(lblFinalLine).addComponent(lblJoinLine))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlConfigLine.createParallelGroup(Alignment.LEADING)
										.addComponent(txtLineWidth, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
										.addComponent(pnlColor, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
										.addComponent(cboLineStyle, 0, 310, Short.MAX_VALUE)
										.addComponent(cboFinalLine, 0, 310, Short.MAX_VALUE)
										.addComponent(cboJoinLine, 0, 310, Short.MAX_VALUE)).addContainerGap()));
		gl_pnlConfigLine.setVerticalGroup(gl_pnlConfigLine.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlConfigLine
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnlConfigLine
										.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(pnlColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlConfigLine
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblWidth)
										.addComponent(txtLineWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlConfigLine
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLineStyle)
										.addComponent(cboLineStyle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlConfigLine
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblFinalLine)
										.addComponent(cboFinalLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(
								gl_pnlConfigLine
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblJoinLine)
										.addComponent(cboJoinLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)).addContainerGap()));
		pnlConfigLine.setLayout(gl_pnlConfigLine);
		contentPanel.setLayout(gl_contentPanel);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSave = new JButton("Salvar");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				btnCancel = new JButton("Cancelar");
				buttonPane.add(btnCancel);
			}
		}
		this.addAdapter();
	}

	private void addAdapter() {
		LineStyleAdapter adapter = new LineStyleAdapter(this);
		btnSave.addActionListener(adapter);
		btnCancel.addActionListener(adapter);
		btnColor.addActionListener(adapter);
		btnAdd.addActionListener(adapter);
		btnDeleteVisual.addActionListener(adapter);
		btnUpArrow.addActionListener(adapter);
		btnDownArrow.addActionListener(adapter);
		btnCopyVisual.addActionListener(adapter);
		tblVisual.getSelectionModel().addListSelectionListener(adapter);
		cboFinalLine.addActionListener(adapter);
		cboJoinLine.addActionListener(adapter);
		cboLineStyle.addActionListener(adapter);
		txtLineWidth.addCaretListener(adapter);
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
	 * @return the txtLineWidth
	 */
	public JTextField getTxtLineWidth() {
		return txtLineWidth;
	}

	/**
	 * @param txtLineWidth
	 *            the txtLineWidth to set
	 */
	public void setTxtLineWidth(JTextField txtLineWidth) {
		this.txtLineWidth = txtLineWidth;
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
	 * @return the btnAdd
	 */
	public JButton getBtnAdd() {
		return btnAdd;
	}

	/**
	 * @param btnAdd
	 *            the btnAdd to set
	 */
	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
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
	 * @return the btnColor
	 */
	public JButton getBtnColor() {
		return btnColor;
	}

	/**
	 * @param btnColor
	 *            the btnColor to set
	 */
	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}

	/**
	 * @return the pnlColor
	 */
	public JPanel getPnlColor() {
		return pnlColor;
	}

	/**
	 * @param pnlColor
	 *            the pnlColor to set
	 */
	public void setPnlColor(JPanel pnlColor) {
		this.pnlColor = pnlColor;
	}

	/**
	 * @return the cboLineStyle
	 */
	public JComboBox getCboLineStyle() {
		return cboLineStyle;
	}

	/**
	 * @param cboLineStyle
	 *            the cboLineStyle to set
	 */
	public void setCboLineStyle(JComboBox cboLineStyle) {
		this.cboLineStyle = cboLineStyle;
	}

	/**
	 * @return the cboFinalLine
	 */
	public JComboBox getCboFinalLine() {
		return cboFinalLine;
	}

	/**
	 * @param cboFinalLine
	 *            the cboFinalLine to set
	 */
	public void setCboFinalLine(JComboBox cboFinalLine) {
		this.cboFinalLine = cboFinalLine;
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
	 * @return the cboJoinLine
	 */
	public JComboBox getCboJoinLine() {
		return cboJoinLine;
	}

	/**
	 * @param cboJoinLine
	 *            the cboJoinLine to set
	 */
	public void setCboJoinLine(JComboBox cboJoinLine) {
		this.cboJoinLine = cboJoinLine;
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
}
