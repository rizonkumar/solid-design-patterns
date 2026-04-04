import { ParkingFloor } from '../models/ParkingFloor';
import { Vehicle } from '../models/Vehicle';
import { DefaultParkingStrategy, ParkingStrategy } from '../strategies/ParkingStrategy';

export class ParkingLot {
  private static instance: ParkingLot;
  private readonly floors: ParkingFloor[] = [];
  private readonly strategy: ParkingStrategy;

  private constructor() {
    this.strategy = new DefaultParkingStrategy();
  }

  public static getInstance(): ParkingLot {
    if (!ParkingLot.instance) {
      ParkingLot.instance = new ParkingLot();
    }
    return ParkingLot.instance;
  }

  public addFloor(floor: ParkingFloor): void {
    this.floors.push(floor);
  }

  public parkVehicle(vehicle: Vehicle): boolean {
    const spot = this.strategy.findSpot(this.floors, vehicle);
    if (spot) {
      spot.park(vehicle);
      console.log(`Parked ${vehicle.getType()} (${vehicle.getLicensePlate()}) in spot.`);
      return true;
    }
    console.log(`No available spot for ${vehicle.getType()} (${vehicle.getLicensePlate()})`);
    return false;
  }

  public unparkVehicle(licensePlate: string): boolean {
    for (const floor of this.floors) {
      for (const spot of floor.getSpots()) {
        const vehicle = spot.getVehicle();
        if (vehicle && vehicle?.getLicensePlate() === licensePlate) {
          spot.unpark();
          console.log(`Unparked vehicle: ${licensePlate}`);
          return true;
        }
      }
    }
    console.log(`Vehicle ${licensePlate} not found.`);
    return false;
  }

  public displayStatus(): void {
    for (const floor of this.floors) {
      console.log(`Floor ${floor.getFloorNumber()} status:`);
      const spots = floor.getSpots();
      const available = spots.filter((s) => s.isAvailable()).length;
      console.log(`Spots: ${available}/${spots.length} available.`);
    }
  }
}
