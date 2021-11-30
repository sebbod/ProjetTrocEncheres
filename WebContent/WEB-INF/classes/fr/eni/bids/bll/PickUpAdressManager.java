package fr.eni.bids.bll;

import fr.eni.bids.BidsException;
import fr.eni.bids.bo.PickUpAdress;

public class PickUpAdressManager extends GenericManager<PickUpAdress> {

	public PickUpAdressManager() throws BidsException {
		super();
	}

	@Override
	protected int[] getIdentifiers(PickUpAdress pickUpAdr) {
		return new int[] { pickUpAdr.getItemId() };
	}

	@Override
	protected void executeUpdate(PickUpAdress pickUpAdr, String operationCRUD) throws BidsException {
		if (operationCRUD.equals("DELETE")) {
			pickUpAdr.getItem().setIsCollected(true);
		}
	}

	/**
	 * Check all the attributes of an pickUpAdr.
	 * 
	 * @param pickUpAdr
	 *            PickUpAdress | PickUpAdress to check.
	 * @throws BidsException
	 *             BidsException | Newly created exception.
	 */
	protected void checkAttributes(PickUpAdress pickUpAdr) throws BidsException {
		if (pickUpAdr == null) {
			throw new BidsException(ErrorCodesBLL.BO_NULL_ERROR.get("PickUpAdress"));
		}
		StringBuilder errors = new StringBuilder();
		if (pickUpAdr.getItemId() == null) {
			errors.append("Champs obligatoire. L'adresse de retrait n'a pas d'article associé.").append("\n");
		}
		if (pickUpAdr.getStreet() == null || pickUpAdr.getStreet().isEmpty()) {
			errors.append("Champs obligatoire. L'adresse de retrait n'a pas de rue renseignée.").append("\n");
		}
		if (pickUpAdr.getZipCode() == null || pickUpAdr.getZipCode().isEmpty()) {
			errors.append("Champs obligatoire. L'adresse de retrait n'a pas de code postal renseigné.").append("\n");
		}
		if (pickUpAdr.getTown() == null || pickUpAdr.getTown().isEmpty()) {
			errors.append("Champs obligatoire. L'adresse de retrait n'a pas de ville renseignée.").append("\n");
		}
		if (!errors.toString().isEmpty()) {
			throw new BidsException(errors.toString());
		}
	}

	/**
	 * Check if a pickUp Address already exists in the database.
	 */
	@Override
	protected boolean exist(PickUpAdress pickUpAdr) throws BidsException {
		return getById(pickUpAdr.getItemId()) != null;
	}

}
