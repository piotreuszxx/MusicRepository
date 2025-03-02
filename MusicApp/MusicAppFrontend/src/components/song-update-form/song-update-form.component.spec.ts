import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SongUpdateFormComponent } from './song-update-form.component';

describe('SongUpdateFormComponent', () => {
  let component: SongUpdateFormComponent;
  let fixture: ComponentFixture<SongUpdateFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SongUpdateFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SongUpdateFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
