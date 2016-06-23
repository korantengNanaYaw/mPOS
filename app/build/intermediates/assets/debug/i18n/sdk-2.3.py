from subprocess import call
import os

os.chdir('displays/20x4')

call(['l10n', 'link', '1iIner9Cx93v6-skJw_RPwX4cKL-mgf8YtK4fKaTY0bw'])
call(['l10n', 'export', '--prefix', 'prompts-20x4-', '--split', '--split_prefix', 'localization', '--fallback', 'en_US'])


call(['l10n', 'link', '19D9Iit3K-dzZmpzB4BSo1Q_QVhJ6yQlq8AaBcUEEsbw'])
call(['l10n', 'export', '--prefix', 'reasons-20x4-', '--split', '--split_prefix', 'localization', '--fallback', 'de_DE'])


os.chdir('../40x2')

call(['l10n', 'link', '12D9hrxH3qA_L_nnmqQ5R3ve4ytOLznBEjACm3G_INkQ'])
call(['l10n', 'export', '--prefix', 'prompts-40x2-', '--split', '--split_prefix', 'localization', '--fallback', 'en_US'])


call(['l10n', 'link', '11Bik-419-cFUbtkzQ8pKpPEtgeREjzArPMa7GZqTdcM'])
call(['l10n', 'export', '--prefix', 'reasons-40x2-', '--split', '--split_prefix', 'localization', '--fallback', 'de_DE'])


os.chdir('../../errors/40x1')

call(['l10n', 'link', '1jl-zOTcC78XlxnLB5kEyA7vGJ0RfP8jOvaVc-i7Mjps'])
call(['l10n', 'export', '--prefix', 'errors-40x1-', '--split', '--split_prefix', 'localization'])


os.chdir('../../receipts')

call(['l10n', 'link', '1vPxSjZBlGOzStz_dcKbD6wQoeiYeZByUFNDGUP4kq2E'])
call(['l10n', 'export', '--prefix', 'receipt-', '--fallback', 'en_US'])



