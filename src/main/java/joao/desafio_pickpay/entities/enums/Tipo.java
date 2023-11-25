package joao.desafio_pickpay.entities.enums;

public enum Tipo {
	COMUM(1),
	LOJISTA(2);
	
	private int numero;

	private Tipo(int numero) {
		this.numero = numero;
	}

	public int getCode() {
		return numero;
	}

	public static Tipo valueOff(int numero) {
		for (Tipo value : Tipo.values()) {
			if (value.getCode() == numero) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invald Tipo code");
	}
}
