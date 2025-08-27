import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TrainApi } from './api/train.service';
import { TrainDto } from './api/train.models';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Train UI';
  train = signal<TrainDto | null>(null);
  types = ['0','A','B'];
  leftType = 'A';
  rightType = 'B';
  replaceIndex = 0;
  replaceType = '0';
  resetValue = '0AAB0';

  constructor(private api: TrainApi) { this.refresh(); }

  refresh() { this.api.get().subscribe(t => this.train.set(t)); }
  doReset() { this.api.reset(this.resetValue).subscribe(t => this.train.set(t)); }
  addLeft() { this.api.addLeft(this.leftType).subscribe(t => this.train.set(t)); }
  addRight() { this.api.addRight(this.rightType).subscribe(t => this.train.set(t)); }
  removeLeft() { this.api.removeLeft().subscribe(t => this.train.set(t)); }
  removeRight() { this.api.removeRight().subscribe(t => this.train.set(t)); }
  replace() { this.api.replace(this.replaceIndex, this.replaceType).subscribe(t => this.train.set(t)); }
  sort() { this.api.sort().subscribe(t => this.train.set(t)); }
}
