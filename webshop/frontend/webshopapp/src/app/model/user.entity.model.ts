export class UserEntity {
    id: number | undefined;
    maxCheckinsAllowed: number | undefined;
    countCheckedInCustomer: number | undefined;
    month: string | undefined;
    day: string | undefined;
    time: number[] | undefined; 
    localDate: number[] | undefined; 
    dayType: string | undefined;
    timeOfDay: string | undefined;
    occupationRatio: number | undefined;  

    // Additional properties for chart data
    x?: Date; // Optional property
    y?: number; // Optional property
}