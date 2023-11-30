// user-list.component.ts
import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { UserEntity } from '../model/user.entity.model';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
})
export class UserListComponent implements OnInit {
  users: UserEntity[] = [];
  selectedMonth: string | null = null; // Add this line

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    // Call getAllUsers to initially populate the user list
    this.userService.getAllUsers().subscribe((data) => {
      this.users = data;
    });
  }


  deleteUser(userId: number | undefined): void {
    if (userId !== undefined) {
      // Call the deleteUser service method
      this.userService.deleteUser(userId).subscribe(
        (data) => {
          // Handle the result as needed
          console.log('User deletion result:', data);

          // If you want to update the user list after deletion, you can reload the data
          this.userService.getAllUsers().subscribe((updatedUsers) => {
            this.users = updatedUsers;
          });
        },
        (error) => {
          // Handle errors if any
          console.error('Error deleting user:', error);
        }
      );
    }
  }

}
