import { VehicleType } from '../constants/Enums';

export abstract class Vehicle {
  constructor(
    private licensePlate: string,
    private type: VehicleType
  ) {}

  public getLicensePlate(): string {
    return this.licensePlate;
  }

  public getType(): VehicleType {
    return this.type;
  }
}

export class Motorcycle extends Vehicle {
  constructor(licensePlate: string) {
    super(licensePlate, VehicleType.MOTORCYCLE);
  }
}

export class Car extends Vehicle {
  constructor(licensePlate: string) {
    super(licensePlate, VehicleType.CAR);
  }
}

export class Bus extends Vehicle {
  constructor(licensePlate: string) {
    super(licensePlate, VehicleType.BUS);
  }
}
