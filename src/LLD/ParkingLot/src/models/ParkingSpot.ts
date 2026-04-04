import { SpotType } from '../constants/Enums';
import { Vehicle } from './Vehicle';

export class ParkingSpot {
  private vehicle: Vehicle | null = null;

  constructor(
    private spotId: string,
    private type: SpotType
  ) {}

  public isAvailable(): boolean {
    return this.vehicle === null;
  }

  public getSpotType(): SpotType {
    return this.type;
  }

  public park(vehicle: Vehicle): void {
    this.vehicle = vehicle;
  }

  public unpark(): void {
    this.vehicle = null;
  }

  public getVehicle(): Vehicle | null {
    return this.vehicle;
  }
}
