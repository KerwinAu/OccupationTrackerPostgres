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
  dps: { x: Date, y: number; id?:number }[] = [];
  chart: any;
  updateInterval!: number;

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

    // Set up the interval for updating the chart
    this.updateInterval = setInterval(this.updateChart, 1000) as any;
  }

  updateChart = () => {
    // Fetch data from the service
    this.userService.getAllUsers().subscribe(
      (users: UserEntity[]) => {
        for (let j = 0; j < users.length; j++) {
          const user = users[j];

          // Extracting data for x, y, and id values
          const xVal = new Date(user.localDate![0], user.localDate![1] - 1, user.localDate![2], user.time![0], user.time![1], user.time![2]);
          const yVal = (user.occupationRatio || 0).toFixed(2); // Provide a default value if occupationRatio is undefined
          const idVal = user.id; // Assuming that 'id' is a property in your UserEntity

          // Push the data to dps
          this.dps.push({ x: xVal, y: +yVal, id: idVal });

          // Shift old data points if the length exceeds the specified data length
          if (this.dps.length > 48) {
            this.dps.shift();
          }
        }

        // Render the updated chart
        this.chart.render();
      },
      error => {
        console.error('Error loading user data:', error);
      }
    );
  };

}
