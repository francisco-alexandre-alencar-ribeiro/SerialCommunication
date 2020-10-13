package comunication;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JOptionPane;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

public class ControlPort {
	private OutputStream serialOut;
	private int rate;
	private String portCOM;

	public ControlPort(String portCOM, int rate) throws Exception {
	    this.portCOM = portCOM;
	    this.rate = rate;
		init();
    }

	private void init() throws Exception {
		CommPortIdentifier portId = null;
		try {
			portId = CommPortIdentifier.getPortIdentifier(this.portCOM);
		} catch (NoSuchPortException e) {
			JOptionPane.showMessageDialog(null, "Porta COM n�o encontrada.", "Porta COM", JOptionPane.PLAIN_MESSAGE);
		}
		
		try {
			SerialPort port = (SerialPort) portId.open("Comunica��o serial", this.rate);
			port.setSerialPortParams(this.rate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			serialOut = port.getOutputStream();
		} catch(PortInUseException e) {
			JOptionPane.showMessageDialog(null, "Porta COM j� est� sendo usada.", "Porta COM", JOptionPane.PLAIN_MESSAGE);
			throw new Exception(e.getMessage());
		}
	}

	public void close() {
		try {
			serialOut.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel fechar porta COM.", "Fechar porta COM", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void sendData(Led led) {
		try {
			serialOut.write(led.getValue());
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel enviar o dado. ", "Enviar dados", JOptionPane.PLAIN_MESSAGE);
		}
	}
}