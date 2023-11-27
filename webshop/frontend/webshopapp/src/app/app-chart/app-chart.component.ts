import { Component, OnInit } from '@angular/core';
import { CanvasJS } from '@canvasjs/angular-charts';
import { UserService } from '../services/user.service';
import { UserEntity } from '../model/user.entity.model';

@Component({
  selector: 'app-app-chart',
  templateUrl: './app-chart.component.html',
  styleUrls: ['./app-chart.component.css']
})
export class AppChartComponent implements OnInit {
  dps: { x: Date, y: number; id?: number }[] = [];
  chart: any;
  updateInterval!: number;
  availableMonths: string[] = [];
  selectedMonth: string = '';

  chartOptions = {
    exportEnabled: true,
    title: {
      text: "Angular Dynamic Chart"
    },
    data: [{
      type: "line",
      dataPoints: this.dps,
      toolTipContent: "ID: {id}, {x}, {y}% " // Add this line to show the ID
    }]
  };


  constructor(private userService: UserService) { }

  ngOnInit(): void {
    // Initialize the chart
    this.chart = new CanvasJS.Chart("chartContainer", this.chartOptions);
    this.chart.render();

    // Fetch available months
    this.userService.getAllUsers().subscribe((users: UserEntity[]) => {
      this.availableMonths = [...new Set(users.map(user => new Date(user.localDate![0], user.localDate![1] - 1, user.localDate![2]).toLocaleString('default', { month: 'long' })))];
    });

    // Set the selected month to the current month
    this.selectedMonth = new Date().toLocaleString('default', { month: 'long' });

    // Update the chart
    this.updateChart();
  }
  onMonthChange(event: any) {
    this.selectedMonth = event.target.value;
    this.updateChart();
  }

  updateChart = () => {
    // Fetch data from the service
    this.userService.getAllUsers().subscribe(
      (users: UserEntity[]) => {
        let groupedData: { [date: string]: number[] } = {};

        // Group the data by date and filter by selected month
        for (let j = 0; j < users.length; j++) {
          const user = users[j];
          const date = new Date(user.localDate![0], user.localDate![1] - 1, user.localDate![2]);
          const month = date.toLocaleString('default', { month: 'long' });

          if (month === this.selectedMonth) {
            const ratio = user.occupationRatio || 0;

            if (!groupedData[date.toDateString()]) {
              groupedData[date.toDateString()] = [];
            }

            groupedData[date.toDateString()].push(ratio);
          }
        }

        // Clear dps before pushing new data points
        this.dps = [];

        // Calculate the average and create data points
        for (let date in groupedData) {
          const ratios = groupedData[date];
          const avgRatio = ratios.reduce((a, b) => a + b, 0) / ratios.length;
          const xVal = new Date(date);
          const yVal = avgRatio.toFixed(2);

          this.dps.push({ x: xVal, y: +yVal });
        }

        // Update the dataPoints property of the chart
        this.chart.options.data[0].dataPoints = this.dps;

        // Render the updated chart
        this.chart.render();
      },
      error => {
        console.error('Error loading user data:', error);
      }
    );
  };

}
