import { Component } from '@angular/core';
import { SongService } from '../../services/song-service';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ISongRequest, ISongResponse } from '../../types';

@Component({
  selector: 'app-song-update-form',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './song-update-form.component.html',
  styleUrl: './song-update-form.component.css'
})
export class SongUpdateFormComponent {
  songUpdateForm: FormGroup;
  songId: string | null = null;

  constructor(
    private fb: FormBuilder,
    private songService: SongService,
    private route: ActivatedRoute
  ) {

    this.songUpdateForm = this.fb.group({
      title: [''],
      releaseYear: [null],
      views: [null],
      genre: [''],
      artistId: [''],
    });


    this.songId = this.route.snapshot.paramMap.get('id');

    if (this.songId) {

      this.songService.findById(this.songId).subscribe({
        next: (song: ISongResponse) => {
          console.log('Fetched song:', song);

          // wypelnij formsa
          this.songUpdateForm.patchValue({
            title: song.title,
            releaseYear: song.releaseYear,
            views: song.views,
            genre: song.genre,
            artistId: song.artistId,
          });
        },
        error: (err) => {
          console.error('Error fetching song:', err);
        }
      });
    } else {
      console.error('Invalid song ID');
    }
  }

  onSubmit() {
    this.updateSong();
  }

  updateSong() {
    // dane z formsa
    const formData = this.songUpdateForm.value as ISongRequest;

    console.log('formData title:', formData.title);
    console.log('formData releaseYear:', formData.releaseYear);
    console.log('formData views: ', formData.views);
    console.log('formData genre: ', formData.genre);

    if (!this.songId) {
      console.error('Invalid song ID');
      return;
    }

    // update backendu
    this.songService.update(this.songId, formData)
      .subscribe({
        next: (data) => {
          console.log('Song updated successfully:', data);
        },
        error: (err) => {
          console.error('Error updating song:', err);
        }
      });
  }
}
