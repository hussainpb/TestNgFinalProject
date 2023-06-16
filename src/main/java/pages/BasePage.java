package pages;

public class BasePage {
	public int generateRandomNumber() {
		return (int) (Math.random() * 1000000 + 1000);
	}
}
