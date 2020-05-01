import {Component, Input} from '@angular/core';
import {Tester} from '../model/tester.model';

@Component({
  selector: 'app-testers-table',
  templateUrl: './testers-table.component.html',
  styles: [
  ]
})
export class TestersTableComponent {

  @Input()
  testers: Tester[];

}
