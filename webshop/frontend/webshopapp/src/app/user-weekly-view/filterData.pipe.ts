// filterData.pipe.ts

import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filterData'
})
export class FilterDataPipe implements PipeTransform {
    transform(items: any[], selectedDay: string, selectedTime: string): any[] {
        // Implement the logic to filter items based on selectedDay and selectedTime
        if (!items || (!selectedDay && !selectedTime)) {
            return items;
        }

        return items.filter(item => {
            return (!selectedDay || item.dayOfWeek === selectedDay) &&
                (!selectedTime || item.timeOfDay === selectedTime);
        });
    }
}
