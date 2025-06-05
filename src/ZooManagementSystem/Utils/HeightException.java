package ZooManagementSystem.Utils;

import ZooManagementSystem.Services.PenguinService;

public class HeightException extends Exception {
	PenguinService penguinService;

	public HeightException(PenguinService penguinService) {
		super();
		this.penguinService = penguinService;
	}

	public HeightException(String message) {
		super(message);
	}

	public void HeightIsIllegal(double height) throws HeightException {
		if (height > penguinService.getLeaderHeight())
			throw new HeightException(
					"Height is taller than the leader of the flock! (" + penguinService.getLeaderHeight() + ")");

		if (!(height >= 1 && height <= penguinService.getLeaderHeight()))
			throw new HeightException("Height of the new Penguin is illegal (Please choose a number between 1-"
					+ penguinService.getLeaderHeight() + " (inclusive))");
	}

}
