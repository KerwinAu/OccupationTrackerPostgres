import { Component, OnInit, AfterViewInit, ElementRef } from '@angular/core';
import { UserService } from '../services/user.service';
import { UserCalcEntity } from '../model/user.calc.entity.model';

declare var CanvasJS: any;

@Component({
  selector: 'user-status',
  templateUrl: './user-status.component.html',
  styleUrls: ['./user-status.component.css']
})
export class UserStatusComponent implements OnInit, AfterViewInit {
  userCalcEntity: UserCalcEntity[] = [];
  currentVisitors: number | undefined;
  maxVisitors: number | undefined;

  constructor(private userService: UserService, private elementRef: ElementRef) { }
  ngOnInit(): void {
    this.userService.getCurrentStatus().subscribe((data) => {
      this.userCalcEntity = data;
      this.currentVisitors = data.countCheckedInCustomer;
      this.maxVisitors = data.maxCheckinsAllowed;

      // Calculate the percentage
      let currentVisitorsPercentage = (this.currentVisitors! / this.maxVisitors!) * 100;
      let maxVisitorsPercentage = 100 - currentVisitorsPercentage;

      let chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        title: {
          text: "Current Visitors"
        },
        data: [{
          type: "pie",
          startAngle: 240,
          yValueFormatString: "##0.00\"%\"",
          indexLabel: "{label} {y}",
          dataPoints: [
            { y: currentVisitorsPercentage, label: "Current Visitors" },
            { y: maxVisitorsPercentage, label: "" }
          ]
        }]
      });

      chart.render();
    });
  }

  ngAfterViewInit(): void {
    let chart = new CanvasJS.Chart("chartContainer", {
      animationEnabled: true,
      title: {
        text: "Current vs Max Visitors"
      },
      data: [{
        type: "pie",
        startAngle: 240,
        yValueFormatString: "##0.00\"%\"",
        indexLabel: "{label} {y}",
        dataPoints: [
          { y: this.currentVisitors, label: "Current Visitors" },
          { y: this.maxVisitors, label: "Max Visitors" }
        ]
      }]
    });

    chart.render();
  }
}