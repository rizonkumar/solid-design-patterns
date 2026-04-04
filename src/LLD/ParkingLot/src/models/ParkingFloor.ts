import { SpotType } from "../constants/Enums";
import { ParkingSpot } from "./ParkingSpot";

export class ParkingFloor {
  private spots: ParkingSpot[] = [];

  constructor(private floorNumber: number) {}

  public addSpot(spot: ParkingSpot): void {
    this.spots.push(spot);
  }

  public getSpots(): ParkingSpot[] {
    return this.spots;
  }

  public getFloorNumber(): number {
    return this.floorNumber;
  }

  public hasFreeSpot(type: SpotType): boolean {
  return this.spots.some((spot) => spot.getSpotType() === type && spot.isAvailable());
 }
}
