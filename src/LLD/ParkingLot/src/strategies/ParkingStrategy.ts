import { SpotType, VehicleType } from '../constants/Enums';
import { ParkingFloor } from '../models/ParkingFloor';
import { ParkingSpot } from '../models/ParkingSpot';
import { Vehicle } from '../models/Vehicle';

export interface ParkingStrategy {
  findSpot(floors: ParkingFloor[], vehicle: Vehicle): ParkingSpot | null;
}

export class DefaultParkingStrategy implements ParkingStrategy {
  public findSpot(floors: ParkingFloor[], vehicle: Vehicle): ParkingSpot | null {
    const requiredType = this.getRequiredSpotType(vehicle.getType());

    for (const floor of floors) {
      for (const spot of floor.getSpots()) {
        if (spot.isAvailable() && spot.getSpotType() === requiredType) {
          return spot;
        }
      }
    }
    return null;
  }

  private getRequiredSpotType(vehicleType: VehicleType): SpotType {
    switch (vehicleType) {
      case VehicleType.MOTORCYCLE:
        return SpotType.SMALL;
      case VehicleType.CAR:
        return SpotType.MEDIUM;
      case VehicleType.BUS:
        return SpotType.LARGE;
      default:
        return SpotType.MEDIUM;
    }
  }
}
