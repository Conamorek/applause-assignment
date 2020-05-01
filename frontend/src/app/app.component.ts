import {Component, OnInit} from '@angular/core';
import {DictionariesService} from './service/dictionaries.service';
import {TesterService} from './testers/tester.service';
import {Tester} from './testers/model/tester.model';
import {Observable} from 'rxjs';
import {share} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  countries: string[];
  devices: string[];
  testers$: Observable<Tester[]>;
  readonly all: string = 'ALL';

  private selectedCountries: string[] = [];
  private selectedDevices: string[] = [];

  constructor(private dictionariesService: DictionariesService,
              private testerService: TesterService) {
  }

  ngOnInit(): void {
    this.dictionariesService.loadAllDictionaries().subscribe(dictionaries => {
      this.countries = dictionaries['testersCountries'];
      this.countries.push(this.all);
      this.devices = dictionaries['devices'];
      this.devices.push(this.all);
    });
  }

  toggleCountry($event): void {
    const selectedCountry = $event.target.innerText;
    this.selectedCountries = this.toggle(selectedCountry, this.selectedCountries);
    this.onButtonClick($event);
  }

  toggleDevice($event): void {
    const selectedDevice = $event.target.innerText;
    this.selectedDevices = this.toggle(selectedDevice, this.selectedDevices);
    this.onButtonClick($event);
  }

  private toggle(value: string, array: string[]): string[] {
    if (array.includes(value)) {
      return array.filter(e => e !== value);
    } else {
      array.push(value);
      return array;
    }
  }

  private onButtonClick($event): void {
    const clickedElement = $event.target;
    if ( clickedElement.nodeName === 'BUTTON') {
      if (clickedElement.classList.contains('btn-success')) {
        clickedElement.classList.remove('btn-success');
      } else {
        clickedElement.className += ' btn-success';
      }
    }
  }

  onFindTesterClick(): void {
    this.testers$ = this.testerService.findTesters(this.selectedCountries, this.selectedDevices).pipe(share());
  }
}
