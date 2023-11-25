package joao.desafio_pickpay.entities.enums;

public enum Estado {
	COM_DINHEIRO(1), SEM_DINHEIRO(2);

	private int numero;

	private Estado(int numero) {
		this.numero = numero;
	}

	public int getCode() {
		return numero;
	}

	public static Estado valueOff(int numero) {
		for (Estado value : Estado.values()) {
			if (value.getCode() == numero) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invald Estado code");
	}

}
