package comunication;

public enum Led {
	LOW('0'), HIGH('1'), BLUE('4'), RED('5');
	
	private char value;
	
	private Led(char value) {
		this.value = value;
	}
	
	public char getValue() {
		return this.value;
	}
}