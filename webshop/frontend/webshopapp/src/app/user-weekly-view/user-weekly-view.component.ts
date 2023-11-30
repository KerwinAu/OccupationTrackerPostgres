// user-weekly-view.component.ts
import { Component, OnInit } from '@angular/core';
import { CanvasJS } from '@canvasjs/angular-charts';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { UserCalcEntity } from '../model/user.calc.entity.model';

@Component({
  selector: 'app-user-weekly-view',
  templateUrl: './user-weekly-view.component.html',
  styleUrls: ['./user-weekly-view.component.css'],
})
export class UserWeeklyViewComponent implements OnInit {
  userCalcEntity: UserCalcEntity[] = [];
  averageRatios: {
    label: string;
    averageRatio: number;
    morning: number;
    afternoon: number;
    evening: number;
  }[] = [];

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.userService.getCalcTable(null, null).subscribe((users: UserCalcEntity[]) => {
      this.userCalcEntity = users;

      // Calculate average ratio for each day of the week
      this.calculateAverageRatios();

      // Call a function to render the bar chart after data is loaded
      this.renderBarChart();
    });
  }

  calculateAverageRatios(): void {
    const dayRatiosMap: {
      [key: string]: {
        total: number;
        count: number;
        morning: number;
        afternoon: number;
        evening: number;
      };
    } = {};

    // Aggregate total, count, morning, afternoon, and evening for each day of the week
    this.userCalcEntity.forEach((user) => {
      const dayOfWeek = user.dayOfWeek || '';
      const avgOccupationRatio = user.avgOccupationRatio || 0;
      const timeOfDay = user.timeOfDay || '';

      if (!dayRatiosMap[dayOfWeek]) {
        dayRatiosMap[dayOfWeek] = {
          total: avgOccupationRatio,
          count: 1,
          morning: 0,
          afternoon: 0,
          evening: 0,
        };
      } else {
        dayRatiosMap[dayOfWeek].total += avgOccupationRatio;
        dayRatiosMap[dayOfWeek].count++;
      }

      // Aggregate morning, afternoon, and evening ratios
      if (timeOfDay === 'Morning') {
        dayRatiosMap[dayOfWeek].morning += avgOccupationRatio;
      } else if (timeOfDay === 'Afternoon') {
        dayRatiosMap[dayOfWeek].afternoon += avgOccupationRatio;
      } else if (timeOfDay === 'Evening') {
        dayRatiosMap[dayOfWeek].evening += avgOccupationRatio;
      }
    });

    // Calculate average ratio for each day and time of day
    this.averageRatios = Object.keys(dayRatiosMap).map((dayOfWeek) => {
      const { total, count, morning, afternoon, evening } = dayRatiosMap[dayOfWeek];
      return {
        label: dayOfWeek,
        averageRatio: total / count,
        morning: morning / count,
        afternoon: afternoon / count,
        evening: evening / count,
      };
    });
  }

  renderBarChart(): void {
    const dataPoints = this.averageRatios.map(({ label, averageRatio }) => ({
      label,
      y: averageRatio,
      indexLabel: `Avg: ${averageRatio.toFixed(2)}`,
    }));

    const chart = new CanvasJS.Chart('chartContainer', {
      animationEnabled: true,
      theme: 'light',
      title: {
        text: 'Average Occupation Ratio by Day of the Week',
      },
      axisY: {
        title: 'Average Occupation Ratio',
      },
      axisX: {
        title: 'Day of the Week',
      },
      data: [
        {
          type: 'column',
          showInLegend: true,
          legendMarkerType: 'none',
          legendText: 'Click on a bar to see details',
          dataPoints: dataPoints,
        },
      ],
    });

    // Add click event handler
    chart.options.data[0].click = (e: { dataPoint: { label: string } }) => {
      const clickedDay = e.dataPoint.label;

      // Navigate to detailed view with the clicked day parameter
      this.router.navigate(['/detailed-view', clickedDay]);
    };

    chart.render();
  }
}
