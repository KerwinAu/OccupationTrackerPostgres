import { Component,OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
// import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

@Component({
  selector: 'app-container',
  // standalone: true,
  templateUrl: './app-container.component.html',
  styleUrls: ['./app-container.component.css'],
  //  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  //  standalone: true
})
export class AppContainerComponent implements OnInit {
  constructor() { } 
  ngOnInit() { }

}
