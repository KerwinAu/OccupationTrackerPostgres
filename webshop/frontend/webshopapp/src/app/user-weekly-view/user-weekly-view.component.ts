import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { UserCalcEntity } from '../model/user.calc.entity.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-weekly-view',
  templateUrl: './user-weekly-view.component.html',
  styleUrls: ['./user-weekly-view.component.css']
})
export class UserWeeklyViewComponent implements OnInit {
  userCalcEntity: UserCalcEntity[] = [];
  daysOfWeek = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
  timesOfDay = ['Morning', 'Afternoon', 'Evening'];
  dayOfWeek = '';
  timeOfDay = '';
  constructor(private userService: UserService) { }

  ngOnInit(): void { }

  onSubmit(form: NgForm): void {

      this.dayOfWeek = form.value.dayOfWeek;
      this.timeOfDay = form.value.timeOfDay;
  

    this.userService.getCalcTable(this.dayOfWeek, this.timeOfDay).subscribe((data) => {
      this.userCalcEntity = data;
    });
  }
}