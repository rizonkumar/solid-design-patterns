import { SpotType } from './constants/Enums';
import { ParkingFloor } from './models/ParkingFloor';
import { ParkingSpot } from './models/ParkingSpot';
import { Bus, Car, Motorcycle } from './models/Vehicle';
import { ParkingLot } from './services/ParkingLot';

const myParkingLot = ParkingLot.getInstance();

const floor1 = new ParkingFloor(1);
floor1.addSpot(new ParkingSpot('1-S-1', SpotType.SMALL));
floor1.addSpot(new ParkingSpot('1-M-1', SpotType.MEDIUM));
floor1.addSpot(new ParkingSpot('1-L-1', SpotType.LARGE));

myParkingLot.addFloor(floor1);

console.log('--- Parking Simulation ---');
const myCar = new Car('KA-01-HH-1234');
const myBike = new Motorcycle('KA-01-EE-5678');
const myBus = new Bus('KA-01-BB-9999');

myParkingLot.parkVehicle(myCar);
myParkingLot.parkVehicle(myBike);
myParkingLot.parkVehicle(myBus);

myParkingLot.displayStatus();

console.log('\n--- Unparking Simulation ---');
myParkingLot.unparkVehicle('KA-01-HH-1234');
myParkingLot.displayStatus();
